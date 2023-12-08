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
    public static void alertDeleteResult(Stage stage, int result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);

        if (result == 1) {
            alert.setTitle("成功");
            alert.setContentText("删除成功！");
        } else {
            alert.setTitle("失败");
            alert.setContentText("删除失败！");
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
    public static void alertInsertUserResult(Integer userId) {
        // 创建用户新增结果的弹窗
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("新增用户结果");

        if (userId != 0) {
            // 插入成功，显示用户新增的id
            alert.setHeaderText("用户新增成功");
            alert.setContentText("新增用户的ID为: " + userId);
        } else {
            // 插入失败
            alert.setHeaderText("用户新增失败");
            alert.setContentText("请检查输入信息并重试");
        }

        // 显示弹窗
        alert.showAndWait();
    }
    public static void alertIsInt(String numberStr){
        try{
            Integer test = Integer.parseInt(numberStr);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setContentText("请输入正确数字");
            alert.showAndWait();
        }
    }
    public static void addButton(GridPane gridPane, String buttonText, Runnable action) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> action.run());
        gridPane.add(button, 1, gridPane.getRowCount());
    }
}
