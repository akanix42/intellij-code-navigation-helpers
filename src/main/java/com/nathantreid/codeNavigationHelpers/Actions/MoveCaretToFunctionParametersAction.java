package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.JSFunction;
import com.intellij.lang.javascript.psi.JSParameter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToFunctionParametersAction extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement element = PsiHelpers.getCurrentElement(event);
        element = PsiHelpers.getSelfOrParentOfType(element, JSFunction.class);
        element = PsiHelpers.getChildElement(element, JSParameter.class);
        PsiHelpers.moveToPsiElement(element);
    }
}
