package UI;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-07
 **/
public class Utils {
    public static void alertQueryFailure(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("失败");
        alert.setContentText("查询失败");
        alert.showAndWait();
    }
    public static void alertUpdateResult(Stage stage, int result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);

        if (result == 1) {
            alert.setTitle("成功");
            alert.setHeaderText("用户信息已成功更新");
            alert.setContentText("用户信息已经成功更新到数据库。");
        } else {
            alert.setTitle("失败");
            alert.setHeaderText("更新用户信息失败");
            alert.setContentText("更新用户信息时发生错误。");
        }
        alert.showAndWait();
    }
    public static void alertReleaseResult(Stage stage, int result){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);

        if (result == 1) {
            alert.setTitle("成功");
            alert.setContentText("商品发布成功。");
        } else {
            alert.setTitle("失败");
            alert.setContentText("商品发布失败。");
        }
        alert.showAndWait();
    }
    public static void alertDoubleInput(Stage stage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText("价格输入错误");
        alert.setContentText("请输入一个大于0的正数作为商品价格！");
        alert.showAndWait();
    }
    public static void addButton(GridPane gridPane, String buttonText, Runnable action) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> action.run());
        gridPane.add(button, 1, gridPane.getRowCount());
    }
}
