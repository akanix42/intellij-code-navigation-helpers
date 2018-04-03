package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.JSArgumentList;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToCall extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement element = PsiHelpers.getCurrentElement(event);
        element = PsiHelpers.getSelfOrParentOfType(element, JSArgumentList.class);
        PsiHelpers.moveToPsiElement(element);
    }
}
