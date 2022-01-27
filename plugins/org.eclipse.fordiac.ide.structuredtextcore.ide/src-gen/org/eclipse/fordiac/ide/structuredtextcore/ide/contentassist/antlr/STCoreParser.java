/*
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.fordiac.ide.structuredtextcore.ide.contentassist.antlr.internal.InternalSTCoreParser;
import org.eclipse.fordiac.ide.structuredtextcore.services.STCoreGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class STCoreParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {
		
		private final Map<AbstractElement, String> mappings;
		
		@Inject
		public NameMappings(STCoreGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}
		
		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}
		
		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, STCoreGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getVarDeclarationAccess().getAlternatives_3_1(), "rule__VarDeclaration__Alternatives_3_1");
			builder.put(grammarAccess.getInitializerExpressionAccess().getAlternatives(), "rule__InitializerExpression__Alternatives");
			builder.put(grammarAccess.getSTStatementAccess().getAlternatives(), "rule__STStatement__Alternatives");
			builder.put(grammarAccess.getSTBranchStatementAccess().getAlternatives(), "rule__STBranchStatement__Alternatives");
			builder.put(grammarAccess.getSTLoopStatementAccess().getAlternatives(), "rule__STLoopStatement__Alternatives");
			builder.put(grammarAccess.getSTAndExpressionAccess().getOpAlternatives_1_0_1_0(), "rule__STAndExpression__OpAlternatives_1_0_1_0");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getOpAlternatives_1_0_1_0(), "rule__STEqualityExpression__OpAlternatives_1_0_1_0");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getOpAlternatives_1_0_1_0(), "rule__STComparisonExpression__OpAlternatives_1_0_1_0");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getOpAlternatives_1_0_1_0(), "rule__STAddSubExpression__OpAlternatives_1_0_1_0");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getOpAlternatives_1_0_1_0(), "rule__STMulDivModExpression__OpAlternatives_1_0_1_0");
			builder.put(grammarAccess.getSTSignumExpressionAccess().getAlternatives(), "rule__STSignumExpression__Alternatives");
			builder.put(grammarAccess.getSTSignumExpressionAccess().getSignumAlternatives_2_1_0(), "rule__STSignumExpression__SignumAlternatives_2_1_0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getAlternatives_1_1(), "rule__STSelectionExpression__Alternatives_1_1");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getAlternatives(), "rule__STAtomicExpression__Alternatives");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getAlternatives(), "rule__STLiteralExpressions__Alternatives");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getKeywordAlternatives_1_0(), "rule__NUMERIC_LITERAL__KeywordAlternatives_1_0");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getAlternatives_2(), "rule__NUMERIC_LITERAL__Alternatives_2");
			builder.put(grammarAccess.getDATE_LITERALAccess().getKeywordAlternatives_0_0(), "rule__DATE_LITERAL__KeywordAlternatives_0_0");
			builder.put(grammarAccess.getTIME_LITERALAccess().getKeywordAlternatives_0_0(), "rule__TIME_LITERAL__KeywordAlternatives_0_0");
			builder.put(grammarAccess.getTIME_OF_DAY_LITERALAccess().getKeywordAlternatives_0_0(), "rule__TIME_OF_DAY_LITERAL__KeywordAlternatives_0_0");
			builder.put(grammarAccess.getDATE_AND_TIME_LITERALAccess().getKeywordAlternatives_0_0(), "rule__DATE_AND_TIME_LITERAL__KeywordAlternatives_0_0");
			builder.put(grammarAccess.getSTRING_LITERALAccess().getAlternatives_0(), "rule__STRING_LITERAL__Alternatives_0");
			builder.put(grammarAccess.getINTEGERAccess().getAlternatives_0(), "rule__INTEGER__Alternatives_0");
			builder.put(grammarAccess.getREALAccess().getAlternatives_2(), "rule__REAL__Alternatives_2");
			builder.put(grammarAccess.getMultiBitAccessSpecifierAccess().getAlternatives(), "rule__MultiBitAccessSpecifier__Alternatives");
			builder.put(grammarAccess.getVarDeclarationBlockAccess().getGroup(), "rule__VarDeclarationBlock__Group__0");
			builder.put(grammarAccess.getVarTempDeclarationBlockAccess().getGroup(), "rule__VarTempDeclarationBlock__Group__0");
			builder.put(grammarAccess.getVarInputDeclarationBlockAccess().getGroup(), "rule__VarInputDeclarationBlock__Group__0");
			builder.put(grammarAccess.getVarOutputDeclarationBlockAccess().getGroup(), "rule__VarOutputDeclarationBlock__Group__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup(), "rule__VarDeclaration__Group__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_1(), "rule__VarDeclaration__Group_1__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_3(), "rule__VarDeclaration__Group_3__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_3_1_0(), "rule__VarDeclaration__Group_3_1_0__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_3_1_0_2(), "rule__VarDeclaration__Group_3_1_0_2__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_3_1_1(), "rule__VarDeclaration__Group_3_1_1__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_3_1_1_2(), "rule__VarDeclaration__Group_3_1_1_2__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_5(), "rule__VarDeclaration__Group_5__0");
			builder.put(grammarAccess.getVarDeclarationAccess().getGroup_6(), "rule__VarDeclaration__Group_6__0");
			builder.put(grammarAccess.getArrayInitializerExpressionAccess().getGroup(), "rule__ArrayInitializerExpression__Group__0");
			builder.put(grammarAccess.getArrayInitializerExpressionAccess().getGroup_2(), "rule__ArrayInitializerExpression__Group_2__0");
			builder.put(grammarAccess.getArrayInitElementAccess().getGroup(), "rule__ArrayInitElement__Group__0");
			builder.put(grammarAccess.getArrayInitElementAccess().getGroup_1(), "rule__ArrayInitElement__Group_1__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_0(), "rule__STStatement__Group_0__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_1(), "rule__STStatement__Group_1__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_2(), "rule__STStatement__Group_2__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_3(), "rule__STStatement__Group_3__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_4(), "rule__STStatement__Group_4__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_5(), "rule__STStatement__Group_5__0");
			builder.put(grammarAccess.getSTStatementAccess().getGroup_6(), "rule__STStatement__Group_6__0");
			builder.put(grammarAccess.getSTAssignmentStatementAccess().getGroup(), "rule__STAssignmentStatement__Group__0");
			builder.put(grammarAccess.getSTIfStatmentAccess().getGroup(), "rule__STIfStatment__Group__0");
			builder.put(grammarAccess.getSTElseIfPartAccess().getGroup(), "rule__STElseIfPart__Group__0");
			builder.put(grammarAccess.getSTCaseStatementAccess().getGroup(), "rule__STCaseStatement__Group__0");
			builder.put(grammarAccess.getSTCaseCasesAccess().getGroup(), "rule__STCaseCases__Group__0");
			builder.put(grammarAccess.getSTCaseCasesAccess().getGroup_1(), "rule__STCaseCases__Group_1__0");
			builder.put(grammarAccess.getSTElsePartAccess().getGroup(), "rule__STElsePart__Group__0");
			builder.put(grammarAccess.getSTForStatementAccess().getGroup(), "rule__STForStatement__Group__0");
			builder.put(grammarAccess.getSTForStatementAccess().getGroup_4(), "rule__STForStatement__Group_4__0");
			builder.put(grammarAccess.getSTWhileStatementAccess().getGroup(), "rule__STWhileStatement__Group__0");
			builder.put(grammarAccess.getSTRepeatStatementAccess().getGroup(), "rule__STRepeatStatement__Group__0");
			builder.put(grammarAccess.getSTSubrangeExpressionAccess().getGroup(), "rule__STSubrangeExpression__Group__0");
			builder.put(grammarAccess.getSTSubrangeExpressionAccess().getGroup_1(), "rule__STSubrangeExpression__Group_1__0");
			builder.put(grammarAccess.getSTSubrangeExpressionAccess().getGroup_1_0(), "rule__STSubrangeExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTOrExpressionAccess().getGroup(), "rule__STOrExpression__Group__0");
			builder.put(grammarAccess.getSTOrExpressionAccess().getGroup_1(), "rule__STOrExpression__Group_1__0");
			builder.put(grammarAccess.getSTOrExpressionAccess().getGroup_1_0(), "rule__STOrExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTXorExpressionAccess().getGroup(), "rule__STXorExpression__Group__0");
			builder.put(grammarAccess.getSTXorExpressionAccess().getGroup_1(), "rule__STXorExpression__Group_1__0");
			builder.put(grammarAccess.getSTXorExpressionAccess().getGroup_1_0(), "rule__STXorExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTAndExpressionAccess().getGroup(), "rule__STAndExpression__Group__0");
			builder.put(grammarAccess.getSTAndExpressionAccess().getGroup_1(), "rule__STAndExpression__Group_1__0");
			builder.put(grammarAccess.getSTAndExpressionAccess().getGroup_1_0(), "rule__STAndExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getGroup(), "rule__STEqualityExpression__Group__0");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getGroup_1(), "rule__STEqualityExpression__Group_1__0");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getGroup_1_0(), "rule__STEqualityExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getGroup(), "rule__STComparisonExpression__Group__0");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getGroup_1(), "rule__STComparisonExpression__Group_1__0");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getGroup_1_0(), "rule__STComparisonExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getGroup(), "rule__STAddSubExpression__Group__0");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getGroup_1(), "rule__STAddSubExpression__Group_1__0");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getGroup_1_0(), "rule__STAddSubExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getGroup(), "rule__STMulDivModExpression__Group__0");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getGroup_1(), "rule__STMulDivModExpression__Group_1__0");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getGroup_1_0(), "rule__STMulDivModExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTPowerExpressionAccess().getGroup(), "rule__STPowerExpression__Group__0");
			builder.put(grammarAccess.getSTPowerExpressionAccess().getGroup_1(), "rule__STPowerExpression__Group_1__0");
			builder.put(grammarAccess.getSTPowerExpressionAccess().getGroup_1_0(), "rule__STPowerExpression__Group_1_0__0");
			builder.put(grammarAccess.getSTSignumExpressionAccess().getGroup_2(), "rule__STSignumExpression__Group_2__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup(), "rule__STSelectionExpression__Group__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1(), "rule__STSelectionExpression__Group_1__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_1_0(), "rule__STSelectionExpression__Group_1_1_0__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_1_1(), "rule__STSelectionExpression__Group_1_1_1__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_1_1_2(), "rule__STSelectionExpression__Group_1_1_1_2__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_2(), "rule__STSelectionExpression__Group_1_2__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_2_1(), "rule__STSelectionExpression__Group_1_2_1__0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getGroup_1_2_1_1(), "rule__STSelectionExpression__Group_1_2_1_1__0");
			builder.put(grammarAccess.getMultibitPartialAccessAccess().getGroup(), "rule__MultibitPartialAccess__Group__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_0(), "rule__STAtomicExpression__Group_0__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_1(), "rule__STAtomicExpression__Group_1__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_1_1(), "rule__STAtomicExpression__Group_1_1__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_1_4(), "rule__STAtomicExpression__Group_1_4__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_1_4_1(), "rule__STAtomicExpression__Group_1_4_1__0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getGroup_1_4_1_1(), "rule__STAtomicExpression__Group_1_4_1_1__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_0(), "rule__STLiteralExpressions__Group_0__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_1(), "rule__STLiteralExpressions__Group_1__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_2(), "rule__STLiteralExpressions__Group_2__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_3(), "rule__STLiteralExpressions__Group_3__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_4(), "rule__STLiteralExpressions__Group_4__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_5(), "rule__STLiteralExpressions__Group_5__0");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getGroup_6(), "rule__STLiteralExpressions__Group_6__0");
			builder.put(grammarAccess.getBOOL_LITERALAccess().getGroup(), "rule__BOOL_LITERAL__Group__0");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getGroup(), "rule__NUMERIC_LITERAL__Group__0");
			builder.put(grammarAccess.getDATE_LITERALAccess().getGroup(), "rule__DATE_LITERAL__Group__0");
			builder.put(grammarAccess.getTIME_LITERALAccess().getGroup(), "rule__TIME_LITERAL__Group__0");
			builder.put(grammarAccess.getTIME_OF_DAY_LITERALAccess().getGroup(), "rule__TIME_OF_DAY_LITERAL__Group__0");
			builder.put(grammarAccess.getDATE_AND_TIME_LITERALAccess().getGroup(), "rule__DATE_AND_TIME_LITERAL__Group__0");
			builder.put(grammarAccess.getSTRING_LITERALAccess().getGroup(), "rule__STRING_LITERAL__Group__0");
			builder.put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
			builder.put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
			builder.put(grammarAccess.getINTEGERAccess().getGroup(), "rule__INTEGER__Group__0");
			builder.put(grammarAccess.getREALAccess().getGroup(), "rule__REAL__Group__0");
			builder.put(grammarAccess.getDATEAccess().getGroup(), "rule__DATE__Group__0");
			builder.put(grammarAccess.getTIME_OF_DAYAccess().getGroup(), "rule__TIME_OF_DAY__Group__0");
			builder.put(grammarAccess.getTIME_OF_DAYAccess().getGroup_5(), "rule__TIME_OF_DAY__Group_5__0");
			builder.put(grammarAccess.getCodeAccess().getStatementsAssignment(), "rule__Code__StatementsAssignment");
			builder.put(grammarAccess.getVarDeclarationBlockAccess().getConstantAssignment_2(), "rule__VarDeclarationBlock__ConstantAssignment_2");
			builder.put(grammarAccess.getVarDeclarationBlockAccess().getVarDeclarationsAssignment_3(), "rule__VarDeclarationBlock__VarDeclarationsAssignment_3");
			builder.put(grammarAccess.getVarTempDeclarationBlockAccess().getConstantAssignment_2(), "rule__VarTempDeclarationBlock__ConstantAssignment_2");
			builder.put(grammarAccess.getVarTempDeclarationBlockAccess().getVarDeclarationsAssignment_3(), "rule__VarTempDeclarationBlock__VarDeclarationsAssignment_3");
			builder.put(grammarAccess.getVarInputDeclarationBlockAccess().getConstantAssignment_2(), "rule__VarInputDeclarationBlock__ConstantAssignment_2");
			builder.put(grammarAccess.getVarInputDeclarationBlockAccess().getVarDeclarationsAssignment_3(), "rule__VarInputDeclarationBlock__VarDeclarationsAssignment_3");
			builder.put(grammarAccess.getVarOutputDeclarationBlockAccess().getConstantAssignment_2(), "rule__VarOutputDeclarationBlock__ConstantAssignment_2");
			builder.put(grammarAccess.getVarOutputDeclarationBlockAccess().getVarDeclarationsAssignment_3(), "rule__VarOutputDeclarationBlock__VarDeclarationsAssignment_3");
			builder.put(grammarAccess.getVarDeclarationAccess().getNameAssignment_0(), "rule__VarDeclaration__NameAssignment_0");
			builder.put(grammarAccess.getVarDeclarationAccess().getLocatedAtAssignment_1_1(), "rule__VarDeclaration__LocatedAtAssignment_1_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getArrayAssignment_3_0(), "rule__VarDeclaration__ArrayAssignment_3_0");
			builder.put(grammarAccess.getVarDeclarationAccess().getRangesAssignment_3_1_0_1(), "rule__VarDeclaration__RangesAssignment_3_1_0_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getRangesAssignment_3_1_0_2_1(), "rule__VarDeclaration__RangesAssignment_3_1_0_2_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getCountAssignment_3_1_1_1(), "rule__VarDeclaration__CountAssignment_3_1_1_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getCountAssignment_3_1_1_2_1(), "rule__VarDeclaration__CountAssignment_3_1_1_2_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getTypeAssignment_4(), "rule__VarDeclaration__TypeAssignment_4");
			builder.put(grammarAccess.getVarDeclarationAccess().getMaxLengthAssignment_5_1(), "rule__VarDeclaration__MaxLengthAssignment_5_1");
			builder.put(grammarAccess.getVarDeclarationAccess().getDefaultValueAssignment_6_1(), "rule__VarDeclaration__DefaultValueAssignment_6_1");
			builder.put(grammarAccess.getArrayInitializerExpressionAccess().getValuesAssignment_1(), "rule__ArrayInitializerExpression__ValuesAssignment_1");
			builder.put(grammarAccess.getArrayInitializerExpressionAccess().getValuesAssignment_2_1(), "rule__ArrayInitializerExpression__ValuesAssignment_2_1");
			builder.put(grammarAccess.getArrayInitElementAccess().getIndexOrInitExpressionAssignment_0(), "rule__ArrayInitElement__IndexOrInitExpressionAssignment_0");
			builder.put(grammarAccess.getArrayInitElementAccess().getInitExpressionAssignment_1_1(), "rule__ArrayInitElement__InitExpressionAssignment_1_1");
			builder.put(grammarAccess.getSTAssignmentStatementAccess().getLhsAssignment_0(), "rule__STAssignmentStatement__LhsAssignment_0");
			builder.put(grammarAccess.getSTAssignmentStatementAccess().getRhsAssignment_2(), "rule__STAssignmentStatement__RhsAssignment_2");
			builder.put(grammarAccess.getSTIfStatmentAccess().getConditionAssignment_1(), "rule__STIfStatment__ConditionAssignment_1");
			builder.put(grammarAccess.getSTIfStatmentAccess().getStatementsAssignment_3(), "rule__STIfStatment__StatementsAssignment_3");
			builder.put(grammarAccess.getSTIfStatmentAccess().getElseifsAssignment_4(), "rule__STIfStatment__ElseifsAssignment_4");
			builder.put(grammarAccess.getSTIfStatmentAccess().getElseAssignment_5(), "rule__STIfStatment__ElseAssignment_5");
			builder.put(grammarAccess.getSTElseIfPartAccess().getConditionAssignment_1(), "rule__STElseIfPart__ConditionAssignment_1");
			builder.put(grammarAccess.getSTElseIfPartAccess().getStatementsAssignment_3(), "rule__STElseIfPart__StatementsAssignment_3");
			builder.put(grammarAccess.getSTCaseStatementAccess().getSelectorAssignment_1(), "rule__STCaseStatement__SelectorAssignment_1");
			builder.put(grammarAccess.getSTCaseStatementAccess().getCasesAssignment_3(), "rule__STCaseStatement__CasesAssignment_3");
			builder.put(grammarAccess.getSTCaseStatementAccess().getElseAssignment_4(), "rule__STCaseStatement__ElseAssignment_4");
			builder.put(grammarAccess.getSTCaseCasesAccess().getConditionsAssignment_0(), "rule__STCaseCases__ConditionsAssignment_0");
			builder.put(grammarAccess.getSTCaseCasesAccess().getConditionsAssignment_1_1(), "rule__STCaseCases__ConditionsAssignment_1_1");
			builder.put(grammarAccess.getSTCaseCasesAccess().getStatementsAssignment_3(), "rule__STCaseCases__StatementsAssignment_3");
			builder.put(grammarAccess.getSTElsePartAccess().getStatementsAssignment_2(), "rule__STElsePart__StatementsAssignment_2");
			builder.put(grammarAccess.getSTForStatementAccess().getForAssignment_1(), "rule__STForStatement__ForAssignment_1");
			builder.put(grammarAccess.getSTForStatementAccess().getToAssignment_3(), "rule__STForStatement__ToAssignment_3");
			builder.put(grammarAccess.getSTForStatementAccess().getByAssignment_4_1(), "rule__STForStatement__ByAssignment_4_1");
			builder.put(grammarAccess.getSTForStatementAccess().getStatementsAssignment_6(), "rule__STForStatement__StatementsAssignment_6");
			builder.put(grammarAccess.getSTWhileStatementAccess().getConditionAssignment_1(), "rule__STWhileStatement__ConditionAssignment_1");
			builder.put(grammarAccess.getSTWhileStatementAccess().getStatementsAssignment_3(), "rule__STWhileStatement__StatementsAssignment_3");
			builder.put(grammarAccess.getSTRepeatStatementAccess().getStatementsAssignment_1(), "rule__STRepeatStatement__StatementsAssignment_1");
			builder.put(grammarAccess.getSTRepeatStatementAccess().getConditionAssignment_3(), "rule__STRepeatStatement__ConditionAssignment_3");
			builder.put(grammarAccess.getSTSubrangeExpressionAccess().getUpperBoundAssignment_1_1(), "rule__STSubrangeExpression__UpperBoundAssignment_1_1");
			builder.put(grammarAccess.getSTOrExpressionAccess().getOpAssignment_1_0_1(), "rule__STOrExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTOrExpressionAccess().getRightAssignment_1_1(), "rule__STOrExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTXorExpressionAccess().getOpAssignment_1_0_1(), "rule__STXorExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTXorExpressionAccess().getRightAssignment_1_1(), "rule__STXorExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTAndExpressionAccess().getOpAssignment_1_0_1(), "rule__STAndExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTAndExpressionAccess().getRightAssignment_1_1(), "rule__STAndExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getOpAssignment_1_0_1(), "rule__STEqualityExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTEqualityExpressionAccess().getRightAssignment_1_1(), "rule__STEqualityExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getOpAssignment_1_0_1(), "rule__STComparisonExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTComparisonExpressionAccess().getRightAssignment_1_1(), "rule__STComparisonExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getOpAssignment_1_0_1(), "rule__STAddSubExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTAddSubExpressionAccess().getRightAssignment_1_1(), "rule__STAddSubExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getOpAssignment_1_0_1(), "rule__STMulDivModExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTMulDivModExpressionAccess().getRightAssignment_1_1(), "rule__STMulDivModExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTPowerExpressionAccess().getOpAssignment_1_0_1(), "rule__STPowerExpression__OpAssignment_1_0_1");
			builder.put(grammarAccess.getSTPowerExpressionAccess().getRightAssignment_1_1(), "rule__STPowerExpression__RightAssignment_1_1");
			builder.put(grammarAccess.getSTSignumExpressionAccess().getSignumAssignment_2_1(), "rule__STSignumExpression__SignumAssignment_2_1");
			builder.put(grammarAccess.getSTSignumExpressionAccess().getExpressionAssignment_2_2(), "rule__STSignumExpression__ExpressionAssignment_2_2");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getStructAccessAssignment_1_1_0_0(), "rule__STSelectionExpression__StructAccessAssignment_1_1_0_0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getMemberAssignment_1_1_0_1(), "rule__STSelectionExpression__MemberAssignment_1_1_0_1");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getArrayAccessAssignment_1_1_1_0(), "rule__STSelectionExpression__ArrayAccessAssignment_1_1_1_0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getIndexAssignment_1_1_1_1(), "rule__STSelectionExpression__IndexAssignment_1_1_1_1");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getIndexAssignment_1_1_1_2_1(), "rule__STSelectionExpression__IndexAssignment_1_1_1_2_1");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getPoeInvocationAssignment_1_2_0(), "rule__STSelectionExpression__PoeInvocationAssignment_1_2_0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getParametersAssignment_1_2_1_0(), "rule__STSelectionExpression__ParametersAssignment_1_2_1_0");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getParametersAssignment_1_2_1_1_1(), "rule__STSelectionExpression__ParametersAssignment_1_2_1_1_1");
			builder.put(grammarAccess.getSTSelectionExpressionAccess().getBitaccessorAssignment_1_3(), "rule__STSelectionExpression__BitaccessorAssignment_1_3");
			builder.put(grammarAccess.getMultibitPartialAccessAccess().getAccessSpecifierAssignment_0(), "rule__MultibitPartialAccess__AccessSpecifierAssignment_0");
			builder.put(grammarAccess.getMultibitPartialAccessAccess().getIndexAssignment_1(), "rule__MultibitPartialAccess__IndexAssignment_1");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getTypeAssignment_1_1_0(), "rule__STAtomicExpression__TypeAssignment_1_1_0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getSymbolAssignment_1_2(), "rule__STAtomicExpression__SymbolAssignment_1_2");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getBitaccessorAssignment_1_3(), "rule__STAtomicExpression__BitaccessorAssignment_1_3");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getPoeInvocationAssignment_1_4_0(), "rule__STAtomicExpression__PoeInvocationAssignment_1_4_0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getParametersAssignment_1_4_1_0(), "rule__STAtomicExpression__ParametersAssignment_1_4_1_0");
			builder.put(grammarAccess.getSTAtomicExpressionAccess().getParametersAssignment_1_4_1_1_1(), "rule__STAtomicExpression__ParametersAssignment_1_4_1_1_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getBoolLiteralAssignment_0_1(), "rule__STLiteralExpressions__BoolLiteralAssignment_0_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getNumericLiteralAssignment_1_1(), "rule__STLiteralExpressions__NumericLiteralAssignment_1_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getDateLiteralAssignment_2_1(), "rule__STLiteralExpressions__DateLiteralAssignment_2_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getTimeLiteralAssignment_3_1(), "rule__STLiteralExpressions__TimeLiteralAssignment_3_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getTimeOfDayLiteralAssignment_4_1(), "rule__STLiteralExpressions__TimeOfDayLiteralAssignment_4_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getTimeLiteralAssignment_5_1(), "rule__STLiteralExpressions__TimeLiteralAssignment_5_1");
			builder.put(grammarAccess.getSTLiteralExpressionsAccess().getStringLiteralAssignment_6_1(), "rule__STLiteralExpressions__StringLiteralAssignment_6_1");
			builder.put(grammarAccess.getBOOL_LITERALAccess().getNotAssignment_0(), "rule__BOOL_LITERAL__NotAssignment_0");
			builder.put(grammarAccess.getBOOL_LITERALAccess().getKeyWordValueAssignment_2(), "rule__BOOL_LITERAL__KeyWordValueAssignment_2");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getNotAssignment_0(), "rule__NUMERIC_LITERAL__NotAssignment_0");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getKeywordAssignment_1(), "rule__NUMERIC_LITERAL__KeywordAssignment_1");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getIntValueAssignment_2_0(), "rule__NUMERIC_LITERAL__IntValueAssignment_2_0");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getRealValueAssignment_2_1(), "rule__NUMERIC_LITERAL__RealValueAssignment_2_1");
			builder.put(grammarAccess.getNUMERIC_LITERALAccess().getHexValueAssignment_2_2(), "rule__NUMERIC_LITERAL__HexValueAssignment_2_2");
			builder.put(grammarAccess.getDATE_LITERALAccess().getKeywordAssignment_0(), "rule__DATE_LITERAL__KeywordAssignment_0");
			builder.put(grammarAccess.getDATE_LITERALAccess().getValueAssignment_1(), "rule__DATE_LITERAL__ValueAssignment_1");
			builder.put(grammarAccess.getTIME_LITERALAccess().getKeywordAssignment_0(), "rule__TIME_LITERAL__KeywordAssignment_0");
			builder.put(grammarAccess.getTIME_LITERALAccess().getValueAssignment_1(), "rule__TIME_LITERAL__ValueAssignment_1");
			builder.put(grammarAccess.getTIME_OF_DAY_LITERALAccess().getKeywordAssignment_0(), "rule__TIME_OF_DAY_LITERAL__KeywordAssignment_0");
			builder.put(grammarAccess.getTIME_OF_DAY_LITERALAccess().getValueAssignment_1(), "rule__TIME_OF_DAY_LITERAL__ValueAssignment_1");
			builder.put(grammarAccess.getDATE_AND_TIME_LITERALAccess().getKeywordAssignment_0(), "rule__DATE_AND_TIME_LITERAL__KeywordAssignment_0");
			builder.put(grammarAccess.getDATE_AND_TIME_LITERALAccess().getDateValueAssignment_1(), "rule__DATE_AND_TIME_LITERAL__DateValueAssignment_1");
			builder.put(grammarAccess.getDATE_AND_TIME_LITERALAccess().getTimeOfDayValueAssignment_3(), "rule__DATE_AND_TIME_LITERAL__TimeOfDayValueAssignment_3");
			builder.put(grammarAccess.getSTRING_LITERALAccess().getKeywordAssignment_0_0(), "rule__STRING_LITERAL__KeywordAssignment_0_0");
			builder.put(grammarAccess.getSTRING_LITERALAccess().getValueAssignment_1(), "rule__STRING_LITERAL__ValueAssignment_1");
		}
	}
	
	@Inject
	private NameMappings nameMappings;

	@Inject
	private STCoreGrammarAccess grammarAccess;

	@Override
	protected InternalSTCoreParser createParser() {
		InternalSTCoreParser result = new InternalSTCoreParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public STCoreGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(STCoreGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
	public NameMappings getNameMappings() {
		return nameMappings;
	}
	
	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
