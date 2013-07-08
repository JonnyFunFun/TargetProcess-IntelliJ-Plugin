/*
 * Copyright (c) 2013 Jonathan Enzinna & TargetProcess, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.targetprocess.intellij;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.util.PasswordUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jdom.Element;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name="TargetProcessSettings",
        storages={
                @Storage(
                        id="main",
                        file="$APP_CONFIG$/targetprocess_settings.xml"
                )
        }
)
public class TargetProcessSettings implements PersistentStateComponent<Element> {

    private static final String SETTINGS_TAG = "TargetProcessSettings";
    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";
    private static final String PATH = "Path";

    private String myLogin;
    private String myPassword;
    private String myTargetProcessPath;

    public static TargetProcessSettings getInstance() {
        return ServiceManager.getService(TargetProcessSettings.class);
    }

    @Nullable
    @Override
    public Element getState() {
        if (StringUtil.isEmptyOrSpaces(myLogin) && StringUtil.isEmptyOrSpaces(myPassword) && StringUtil.isEmptyOrSpaces(myTargetProcessPath)) {
            return null;
        }
        final Element element = new Element(SETTINGS_TAG);
        element.setAttribute(LOGIN, getUsername());
        element.setAttribute(PASSWORD, getEncodedPassword());
        element.setAttribute(PATH, getPath());
        return element;
    }

    @Override
    public void loadState(@NotNull final Element element) {
        try {
            setUsername(element.getAttributeValue(LOGIN));
            setEncodedPassword(element.getAttributeValue(PASSWORD));
            setPath(element.getAttributeValue(PATH));
        }
        catch (Exception e) {
            // ignore
        }
    }

    public String getEncodedPassword() {
        return PasswordUtil.encodePassword(getPassword());
    }

    public void setEncodedPassword(final String password) {
        try {
            setPassword(PasswordUtil.decodePassword(password));
        }
        catch (NumberFormatException e) {
            // do nothing
        }
    }

    @NotNull
    public String getUsername() {
        return myLogin != null ? myLogin : "";
    }

    @NotNull
    public String getPassword() {
        return myPassword != null ? myPassword : "";
    }

    @NotNull
    public String getPath() {
        return myTargetProcessPath != null ? myTargetProcessPath : "";
    }

    public void setUsername(String login) {
        myLogin = login != null ? login : "";
    }

    public void setPassword(String password) {
        myPassword = password != null ? password : "";
    }

    public void setPath(String path) {
        myTargetProcessPath = path != null ? path : "";
    }
}
