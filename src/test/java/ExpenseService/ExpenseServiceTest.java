package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL, "internal project");
        // when
        ExpenseType result = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE, result);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project A");

        // when
        ExpenseType result = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A, result);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project B");

        // when
        ExpenseType result = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B, result);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "project C");

        // when
        ExpenseType result = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE, result);

    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "project D");

        try{
            //when
            ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        }catch (UnexpectedProjectTypeException ex) {
            //then
            assertTrue(ex.getMessage().contains("You enter invalid project type"));
        }
    }
}