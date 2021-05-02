package com.gt.homeNas.application.controller;

import com.gt.homeNas.Infrastructure.persistence.PropertySaveUtil;
import com.gt.homeNas.Infrastructure.repository.UserRepositoryImpl;
import com.gt.homeNas.application.App;
import com.gt.homeNas.application.common.AppContext;
import com.gt.homeNas.application.common.StageManager;
import com.gt.homeNas.application.window.MainWindow;
import com.gt.homeNas.domain.entity.User;
import com.gt.homeNas.domain.exception.UserAccountNotFoundException;
import com.gt.homeNas.domain.exception.UserAccountPasswdErrorException;
import com.gt.homeNas.domain.service.LoginService;
import com.gt.homeNas.domain.service.impl.LoginServiceImpl;
import com.gt.homeNas.types.UserAccount;
import com.gt.homeNas.types.UserPasswd;
import com.gt.homeNas.types.exception.UserAccountInvalidError;
import com.gt.homeNas.types.exception.UserPasswdInvalidError;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController extends AppContext implements Initializable {
    public PasswordField userPasswdInput;
    public TextField userAccountInput;
    public Button loginButton;
    public CheckBox loginStillCheckBox;
    public CheckBox confirmClauseCheckBox;

    private static final String STILL_LOGIN = "STILL_LOGIN";
    private static final String CONFIRM_CLAUSE = "CONFIRM_CLAUSE";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String result = PropertySaveUtil.getKey(STILL_LOGIN);
        LogManager.getLogger().info(String.format("初始化用户登录状态：%s", result));
        loginStillCheckBox.selectedProperty().setValue("TRUE".equals(result));

        String ccResult = PropertySaveUtil.getKey(CONFIRM_CLAUSE);
        LogManager.getLogger().info(String.format("初始化用户条款状态：%s", result));
        confirmClauseCheckBox.selectedProperty().setValue("TRUE".equals(ccResult));
    }

    public void typingPasswd(InputMethodEvent inputMethodEvent) {

    }

    public void typingUserAccount(InputMethodEvent inputMethodEvent) {


    }

    public void enterLogin(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.NUMBER_SIGN)) {
            userControlLogin();
        }
    }

    public void userStillLogin(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) actionEvent.getSource();
            if (chk.isSelected()) {
                LogManager.getLogger().info("用户选择保持登录状态");
                saveStillLogin();
            } else {
                LogManager.getLogger().info("用户选择不保持登录状态");
                removeStillLogin();
            }
        }
    }

    public void confirmClause(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) actionEvent.getSource();
            if (chk.isSelected()) {
                LogManager.getLogger().info("用户选择同意用户条款与隐私协议");
                saveConfirmClause();
            } else {
                LogManager.getLogger().info("用户选择不同意用户条款与隐私协议");
                removeConfirmClause();
            }
        }
    }

    private void userControlLogin() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (!isConfirmClause()) {
            alert.setContentText("请先同意用户条款与隐私协议");
            alert.showAndWait();
            return;
        }
        try {
            UserAccount userAccount = new UserAccount(userAccountInput.getText());
            UserPasswd userPasswd = new UserPasswd(userPasswdInput.getText());
            LogManager.getLogger().info("用户："+userAccount.getUserAccount()
                    +"登录密码："+userPasswd.getUserPasswd()+"尝试登陆...");
            login(userAccount, userPasswd);
        } catch (UserAccountInvalidError | UserAccountNotFoundException
                | UserPasswdInvalidError | UserAccountPasswdErrorException e) {
            e.printStackTrace();
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void userLogin(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            userControlLogin();
        }
    }

    private boolean isConfirmClause() {
        return confirmClauseCheckBox.isSelected();
    }

    private void login(UserAccount userAccount, UserPasswd userPasswd)
            throws UserAccountPasswdErrorException, UserAccountNotFoundException {
        LoginService userLoginService = new LoginServiceImpl(new UserRepositoryImpl());
        User user = userLoginService.generalLogin(userAccount, userPasswd);
        CACHE.put("USER", user);
        LogManager.getLogger().info("用  户："+user.toString()+"登录成功");
        if(loginStillCheckBox.isSelected()) {
            saveUserAccountAndPasswd(userAccount, userPasswd);
        }

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        StageManager<AppContext> appContextStageManager = newWindow("/controller/main.fxml");
        appContextStageManager.setOnShowing(event -> {
            LogManager.getLogger().info("setOnShowing");
        });
        appContextStageManager.setOnShown(event -> {
            LogManager.getLogger().info("setOnShown");
            appContextStageManager.getT().initData();
        });
        appContextStageManager.show();
    }

    private void saveStillLogin() {
        if(PropertySaveUtil.saveKey(STILL_LOGIN, "TRUE")) {
            LogManager.getLogger().info("保存自动登陆选项");
        } else {
            LogManager.getLogger().info("自动登陆选项保存失败");
        }
    }

    private void removeStillLogin() {
        if (PropertySaveUtil.getKey(STILL_LOGIN).equals("TRUE")) {
            PropertySaveUtil.saveKey(STILL_LOGIN,"FALSE");
        }
    }

    private void saveConfirmClause() {
        if(PropertySaveUtil.saveKey(CONFIRM_CLAUSE, "TRUE")) {
            LogManager.getLogger().info("用户同意条款保存成功");
        } else {
            LogManager.getLogger().info("用户同意条款保存失败");
        }
    }

    private void removeConfirmClause() {
        if (PropertySaveUtil.getKey(CONFIRM_CLAUSE).equals("TRUE")) {
            PropertySaveUtil.saveKey(CONFIRM_CLAUSE,"FALSE");
            LogManager.getLogger().info("用户不同意条款保存失败");
        }
    }

    /**
     * 保存用户登录的账号密码
     * @param userAccount 用户名
     * @param userPasswd 用户密码
     */
    private void saveUserAccountAndPasswd(UserAccount userAccount, UserPasswd userPasswd) {
        if (PropertySaveUtil.saveKey(userAccount.getCACHE_KEY(),userAccount.getUserAccount())){
            LogManager.getLogger().info("用户名保存成功");
        }

        if (PropertySaveUtil.saveKey(userPasswd.getCACHE_KEY(), userPasswd.getUserPasswd())) {
            LogManager.getLogger().info("用户密码保存成功");
        }
    }
}
