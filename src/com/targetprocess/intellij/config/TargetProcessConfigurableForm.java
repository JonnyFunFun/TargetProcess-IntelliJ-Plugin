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

package com.targetprocess.intellij.config;

import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargetProcessConfigurableForm {

    private JPanel myPane;
    private JTextField myUsername;
    private JPasswordField myPassword;
    private JTextField myPath;
    private JButton myTestButton;

    public TargetProcessConfigurableForm() {
        myTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                Messages.showInfoMessage(result ? "success" : "cannot-login",
                        result ? "success" : "failure");
            }
        });
    }

    public JComponent getComponent() {
        return myPane;
    }

    public String getPassword() {
        return String.valueOf(myPassword.getPassword());
    }

    public void setPassword(String password) {
        myPassword.setText(password);
    }

    public String getUsername() {
        return myUsername.getText().trim();
    }

    public void setUsername(String username) {
        myUsername.setText(username);
    }

    public String getPath() {
        return myPath.getText().trim();
    }

    public void setPath(String path) {
        myPath.setText(path);
    }
}
