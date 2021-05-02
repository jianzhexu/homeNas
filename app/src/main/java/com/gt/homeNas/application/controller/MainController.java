package com.gt.homeNas.application.controller;

import com.gt.homeNas.application.common.AppContext;
import com.gt.homeNas.domain.entity.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController extends AppContext implements Initializable {
    public Button resourceManagerButton;
    public Button privateCloudButton;
    public Button settingsButton;
    public ScrollPane mainContentPanel;
    public HBox titleHBox;
    public ImageView userIconImage;
    public Label userNameLabel;

    private Parent rManagerView,privateCloudView,userSettingsView = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadResourceManagerView();
        LogManager.getLogger().info("主页面开始加载");
        Scene scene = userNameLabel.getScene();
    }

    private void loadResourceManagerView() {
        if (rManagerView == null) {
            try {
                rManagerView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/resourceManager.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainContentPanel.setContent(rManagerView);
    }

    private void loadPrivateCloudView() {
        if (privateCloudView == null) {
            try {
                privateCloudView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/privateCloud.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainContentPanel.setContent(privateCloudView);
    }

    private void loadUserSettingsView() {
        if (userSettingsView == null) {
            try {
                userSettingsView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/settings.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainContentPanel.setContent(userSettingsView);
    }

    public void openResourceManager(MouseEvent mouseEvent) {
        loadResourceManagerView();
    }

    public void openPrivateCloud(MouseEvent mouseEvent) {
        loadPrivateCloudView();
    }

    public void openSettings(MouseEvent mouseEvent) {
        loadUserSettingsView();
    }

    @Override
    public void initData() {
        LogManager.getLogger().info("调用initData");
        User user = (User) CACHE.getIfPresent("USER");
        if (null != user) {
            userNameLabel.setText(user.getUserName().getName());
        }
    }
}
