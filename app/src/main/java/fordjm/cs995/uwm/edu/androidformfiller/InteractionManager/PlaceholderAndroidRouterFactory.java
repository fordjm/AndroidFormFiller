package fordjm.cs995.uwm.edu.androidformfiller.InteractionManager;

import formfiller.delivery.controller.AddAnswerController;
import formfiller.delivery.controller.AddAnswerCountBoundaryController;
import formfiller.delivery.controller.AddFormComponentController;
import formfiller.delivery.controller.AddOptionController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.controller.ChangeFormatController;
import formfiller.delivery.controller.ChangeIdController;
import formfiller.delivery.controller.DeleteFormComponentController;
import formfiller.delivery.router.Router;

public class PlaceholderAndroidRouterFactory {
    public static Router makeRouter() {
        Router result = new Router();
        result.addMethod("add answer", new AddAnswerController());
        result.addMethod("add answer boundary", new AddAnswerCountBoundaryController());
        result.addMethod("add form component", new AddFormComponentController());
        result.addMethod("add option", new AddOptionController());
        result.addMethod("ask question", new AskQuestionController());
        result.addMethod("change id", new ChangeIdController());
        result.addMethod("change format", new ChangeFormatController());
        result.addMethod("delete form component", new DeleteFormComponentController());
        return result;
    }
}
