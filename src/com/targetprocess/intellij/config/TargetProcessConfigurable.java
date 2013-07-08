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

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.targetprocess.intellij.TargetProcessSettings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by jonathan on 7/8/13.
 */
public class TargetProcessConfigurable implements Configurable {

    private TargetProcessConfigurableForm configurableForm;
    private final TargetProcessSettings mySettings;

    public TargetProcessConfigurable() {
        mySettings = TargetProcessSettings.getInstance();
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "TargetProcess";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "settings.targetprocess";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (configurableForm == null)
            configurableForm = new TargetProcessConfigurableForm();
        reset();
        return configurableForm.getComponent();
    }

    @Override
    public boolean isModified() {
        return configurableForm == null || !mySettings.getPassword().equals(configurableForm.getPassword()) ||
            !mySettings.getUsername().equals(configurableForm.getUsername()) || !mySettings.getPath().equals(configurableForm.getPath());
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configurableForm != null) {
            mySettings.setUsername(configurableForm.getUsername());
            mySettings.setPassword(configurableForm.getPassword());
            mySettings.setPath(configurableForm.getPath());
        }
    }

    @Override
    public void reset() {
        if (configurableForm != null) {
            configurableForm.setUsername(mySettings.getUsername());
            configurableForm.setPassword(mySettings.getPassword());
            configurableForm.setPath(mySettings.getPath());
        }
    }

    @Override
    public void disposeUIResources() {
        configurableForm = null;
    }
}
