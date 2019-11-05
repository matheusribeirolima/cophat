package com.matheus.cophat.helper

import android.content.Context
import com.matheus.cophat.data.local.entity.*
import org.apache.poi.hssf.record.cf.BorderFormatting
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.util.CellRangeAddress
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class ExportWorkbook(private val context: Context, private val resourceManager: ResourceManager) {

    private val headerRow = 0
    private val descriptionColumn = 0
    private val questionsSize = 35
    private val workBook = HSSFWorkbook()
    private lateinit var boldStyle: HSSFCellStyle
    private lateinit var centerWhiteStyle: HSSFCellStyle
    private lateinit var centerCornflowerStyle: HSSFCellStyle
    private lateinit var centerGreenStyle: HSSFCellStyle
    private lateinit var centerYellowStyle: HSSFCellStyle
    private lateinit var centerBlueStyle: HSSFCellStyle
    private lateinit var questions: List<Question>

    fun exportQuestionnaires(
        questionnaires: Array<Questionnaire>,
        categories: List<Category>,
        questionsList: List<Question>?
    ) {
        questionsList?.let { questions = it }
        generateCenterColoredStyles()
        generateBoldStyle()

        val sheetChildren = workBook.createSheet("Crianças e Adolescentes")
        createHeader(sheetChildren, questionnaires)

        val sheetParents = workBook.createSheet("Pais e Responsáveis")
        createHeader(sheetParents, questionnaires)

        val sheetComparative = workBook.createSheet("Comparativo")

        var answerColumn = descriptionColumn + 1
        var answerColumnComparative = descriptionColumn + 1
        for (questionnaire in questionnaires) {
            questionnaire.childApplication?.let { application ->
                generateQuestionnaire(sheetChildren, application, categories, answerColumn)
            }
            questionnaire.parentApplication?.let { application ->
                generateQuestionnaire(sheetParents, application, categories, answerColumn)
            }

            questionnaire.childApplication?.let { childApplication ->
                questionnaire.parentApplication?.let { parentApplication ->
                    createCategoriesComparative(sheetComparative, categories)
                    val childrenAnswers = childApplication.answers?.values?.toList()
                        ?.sortedBy { answer -> answer.id }
                    val parentsAnswers = parentApplication.answers?.values?.toList()
                        ?.sortedBy { answer -> answer.id }
                    createSubTotalsComparative(
                        sheetComparative,
                        categories,
                        childrenAnswers,
                        parentsAnswers,
                        answerColumnComparative,
                        questionnaire.familyId,
                        answerColumn
                    )
                    createTotalsComparative(
                        sheetComparative,
                        childrenAnswers,
                        parentsAnswers,
                        answerColumnComparative
                    )
                    answerColumnComparative += 2
                }
            }
            answerColumn++
        }

        if (questionnaires.size > 1) {
            generateFile("Cophat")
        } else {
            generateFile(questionnaires[0].familyId)
        }
    }

    private fun generateQuestionnaire(
        sheet: HSSFSheet,
        application: ApplicationEntity,
        categories: List<Category>,
        answerColumn: Int
    ) {
        createQuestions(sheet)
        createDescriptions(sheet)
        createCategories(sheet, categories)
        application.answers?.let {
            val sortedAnswers = it.values.toList().sortedBy { answer -> answer.id }
            createAnswers(sheet, sortedAnswers, answerColumn)
            createTotals(sheet, sortedAnswers, answerColumn)
            createSubTotals(sheet, categories, sortedAnswers, answerColumn)
        }
    }

    private fun generateCenterColoredStyles() {
        centerWhiteStyle = workBook.createCellStyle().apply {
            addAlignment(this)
            addBorders(this)
            fillPattern = HSSFCellStyle.SOLID_FOREGROUND
            fillForegroundColor = HSSFColor.WHITE.index
        }

        centerCornflowerStyle = workBook.createCellStyle().apply {
            addAlignment(this)
            addBorders(this)
            fillPattern = HSSFCellStyle.SOLID_FOREGROUND
            fillForegroundColor = HSSFColor.LIGHT_CORNFLOWER_BLUE.index
        }

        centerGreenStyle = workBook.createCellStyle().apply {
            addAlignment(this)
            addBorders(this)
            fillPattern = HSSFCellStyle.SOLID_FOREGROUND
            fillForegroundColor = HSSFColor.LIGHT_GREEN.index
        }

        centerYellowStyle = workBook.createCellStyle().apply {
            addAlignment(this)
            addBorders(this)
            fillPattern = HSSFCellStyle.SOLID_FOREGROUND
            fillForegroundColor = HSSFColor.LIGHT_YELLOW.index
        }

        centerBlueStyle = workBook.createCellStyle().apply {
            addAlignment(this)
            addBorders(this)
            fillPattern = HSSFCellStyle.SOLID_FOREGROUND
            fillForegroundColor = HSSFColor.PALE_BLUE.index
        }
    }

    private fun generateBoldStyle() {
        boldStyle = workBook.createCellStyle().apply {
            addFontBold(this)
            addAlignment(this)
            addBorders(this)
        }
    }

    private fun addFontBold(style: HSSFCellStyle) {
        val boldFont = workBook.createFont()
        boldFont.boldweight = HSSFFont.BOLDWEIGHT_BOLD

        style.apply {
            setFont(boldFont)
            alignment = CellStyle.ALIGN_CENTER
            verticalAlignment = CellStyle.VERTICAL_CENTER
        }
    }

    private fun addAlignment(style: HSSFCellStyle) {
        style.apply {
            alignment = CellStyle.ALIGN_CENTER
            verticalAlignment = CellStyle.VERTICAL_CENTER
        }
    }

    private fun addBorders(style: HSSFCellStyle) {
        style.apply {
            borderBottom = BorderFormatting.BORDER_THIN
            borderTop = BorderFormatting.BORDER_THIN
            borderLeft = BorderFormatting.BORDER_THIN
            borderRight = BorderFormatting.BORDER_THIN
        }
    }

    private fun getStyleByCategory(categoryType: CategoryType?): HSSFCellStyle {
        return when (categoryType) {
            CategoryType.UNDERSTANDING_DISEASE -> centerWhiteStyle
            CategoryType.HOSPITALIZATION -> centerCornflowerStyle
            CategoryType.TREATMENT_SUCCESS -> centerGreenStyle
            CategoryType.COLLATERAL_EFFECTS -> centerYellowStyle
            CategoryType.SCHOOL_EXPECTATION -> centerBlueStyle
            else -> centerWhiteStyle
        }
    }

    private fun createHeader(sheet: HSSFSheet, questionnaires: Array<Questionnaire>) {
        val headerRow = sheet.createRow(headerRow)
        headerRow.createCell(descriptionColumn).apply {
            setCellValue("QUESTÕES")
            setCellStyle(boldStyle)
        }

        var answerColumn = descriptionColumn + 1
        for (questionnaire in questionnaires) {
            headerRow.createCell(answerColumn).apply {
                setCellValue(questionnaire.familyId)
                setCellStyle(boldStyle)
            }
            sheet.setColumnWidth(answerColumn, 6000)
            answerColumn++
        }
    }

    private fun createQuestions(sheet: HSSFSheet) {
        var questionsRow: HSSFRow
        for (position in 1..questionsSize) {
            questionsRow = sheet.createRow(position)
            questionsRow.createCell(descriptionColumn).apply {
                setCellValue("$position")
                setCellStyle(getStyleByCategory(questions[position - 1].category))
            }
        }
    }

    private fun createDescriptions(sheet: HSSFSheet) {
        val categoriesPositionRow = questionsSize + 3
        val categoriesHeaderRow = sheet.createRow(categoriesPositionRow)
        categoriesHeaderRow.createCell(descriptionColumn).apply {
            setCellValue("CATEGORIA")
            setCellStyle(boldStyle)
        }

        val totalPositionRow = questionsSize + 1
        val totalDescriptionRow = sheet.createRow(totalPositionRow)
        totalDescriptionRow.createCell(descriptionColumn).apply {
            setCellValue("TOTAL")
            setCellStyle(boldStyle)
        }
    }

    private fun createCategories(sheet: HSSFSheet, categories: List<Category>) {
        var categoriesPositionRow = questionsSize + 4

        var categoriesRow: HSSFRow
        for (category in categories) {
            categoriesRow = sheet.createRow(categoriesPositionRow)
            categoriesRow.createCell(descriptionColumn).apply {
                setCellValue(category.description)
                setCellStyle(getStyleByCategory(category.type))
            }
            categoriesPositionRow++
        }
        sheet.setColumnWidth(descriptionColumn, 7500)
    }

    private fun createCategoriesComparative(sheet: HSSFSheet, categories: List<Category>) {
        val categoriesHeaderRow = sheet.createRow(0)
        categoriesHeaderRow.createCell(descriptionColumn).apply {
            setCellValue("CATEGORIA")
            setCellStyle(boldStyle)
        }

        var categoriesPositionRow = 1

        var categoriesRow: HSSFRow
        for (category in categories) {
            categoriesRow = sheet.createRow(categoriesPositionRow)
            categoriesRow.createCell(descriptionColumn).apply {
                setCellValue(category.description)
                setCellStyle(getStyleByCategory(category.type))
            }
            categoriesPositionRow++
        }
        sheet.setColumnWidth(descriptionColumn, 7500)
    }

    private fun createAnswers(sheet: HSSFSheet, answers: List<Answer>, answerColumn: Int) {
        var answerRow: HSSFRow
        var answer: Int?
        for (position in 1..answers.size) {
            answerRow = sheet.getRow(position)
            answer = answers[position - 1].chosenAnswer?.chosenAnswerPoints
            answerRow.createCell(answerColumn).apply {
                setCellValue("$answer")
                setCellStyle(getStyleByCategory(questions[position - 1].category))
            }
        }
    }

    private fun createTotals(sheet: HSSFSheet, answers: List<Answer>, answerColumn: Int) {
        val totalPositionRow = questionsSize + 1
        val totalRow: HSSFRow = sheet.getRow(totalPositionRow)

        var total = 0
        for (answer in answers) {
            total += (answer.chosenAnswer?.chosenAnswerPoints ?: 0)
        }
        totalRow.createCell(answerColumn).apply {
            setCellValue("$total")
            setCellStyle(boldStyle)
        }
    }

    private fun createTotalsComparative(
        sheet: HSSFSheet,
        childrenAnswers: List<Answer>?,
        parentsAnswers: List<Answer>?,
        answerColumnComparative: Int
    ) {
        val totalPositionRow = 6
        val totalRow: HSSFRow = sheet.createRow(totalPositionRow)

        val totalDescriptionRow = sheet.createRow(totalPositionRow)
        totalDescriptionRow.createCell(descriptionColumn).apply {
            setCellValue("TOTAL")
            setCellStyle(boldStyle)
        }

        var childrenTotal = 0
        childrenAnswers?.let {
            for (answer in childrenAnswers) {
                childrenTotal += (answer.chosenAnswer?.chosenAnswerPoints ?: 0)
            }
        }
        totalRow.createCell(answerColumnComparative).apply {
            setCellValue("$childrenTotal")
            setCellStyle(boldStyle)
        }

        var parentsTotal = 0
        parentsAnswers?.let {
            for (answer in parentsAnswers) {
                parentsTotal += (answer.chosenAnswer?.chosenAnswerPoints ?: 0)
            }
        }
        totalRow.createCell(answerColumnComparative + 1).apply {
            setCellValue("$parentsTotal")
            setCellStyle(boldStyle)
        }
    }

    private fun createSubTotals(
        sheet: HSSFSheet,
        categories: List<Category>,
        answers: List<Answer>,
        answerColumn: Int
    ) {
        var categoriesPositionRow = questionsSize + 4

        var categoriesRow: HSSFRow
        for (category in categories) {
            categoriesRow = sheet.getRow(categoriesPositionRow)

            val categoryPoints =
                answers.filter { questions[it.id - 1].category == category.type }
                    .sumBy { it.chosenAnswer?.chosenAnswerPoints ?: 0 }
            categoriesRow.createCell(answerColumn).apply {
                setCellValue("$categoryPoints")
                setCellStyle(getStyleByCategory(category.type))
            }
            categoriesPositionRow++
        }
    }

    private fun createSubTotalsComparative(
        sheet: HSSFSheet,
        categories: List<Category>,
        childrenAnswers: List<Answer>?,
        parentsAnswers: List<Answer>?,
        answerColumnComparative: Int,
        familyId: String,
        answerColumn: Int
    ) {
        val subTotalHeader = sheet.getRow(0).createCell(answerColumn)
        sheet.addMergedRegion(CellRangeAddress(0, 0, 1, 2))
        subTotalHeader.setCellValue(familyId)
        subTotalHeader.setCellStyle(boldStyle)

        var categoriesPositionRow = 1

        var categoriesRow: HSSFRow
        for (category in categories) {
            categoriesRow = sheet.getRow(categoriesPositionRow)

            val categoryChildrenPoints =
                childrenAnswers?.filter { questions[it.id - 1].category == category.type }
                    ?.sumBy { it.chosenAnswer?.chosenAnswerPoints ?: 0 }
            categoriesRow.createCell(answerColumnComparative).apply {
                setCellValue("$categoryChildrenPoints")
                setCellStyle(getStyleByCategory(category.type))
            }

            val categoryParentsPoints =
                parentsAnswers?.filter { questions[it.id - 1].category == category.type }
                    ?.sumBy { it.chosenAnswer?.chosenAnswerPoints ?: 0 }
            categoriesRow.createCell(answerColumnComparative + 1).apply {
                setCellValue("$categoryParentsPoints")
                setCellStyle(getStyleByCategory(category.type))
            }

            categoriesPositionRow++
        }
        sheet.setColumnWidth(answerColumnComparative, 3500)
        sheet.setColumnWidth(answerColumnComparative + 1, 3500)
    }

    private fun generateFile(familyId: String) {
        val file = File(context.getExternalFilesDir(null), "$familyId.xls")
        try {
            val os = FileOutputStream(file)
            workBook.write(os)
            os.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            context.showToast("Arquivo não encontrado")
        } catch (e: IOException) {
            e.printStackTrace()
            context.showToast("Erro ao salvar arquivo")
        }
    }
}