package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.JSParameter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToPreviousParameter extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement element = PsiHelpers.getCurrentElement(event);
        PsiElement currentParameter = PsiHelpers.getSelfOrParentOfType(element, JSParameter.class);
        if (currentParameter == null) {
            currentParameter = element;
        }

        PsiElement previousParameter = PsiHelpers.getPreviousSiblingOfType(currentParameter, JSParameter.class);
        /* If there is no previous parameter, move the beginning of the current parameter */
        if (previousParameter == null) {
            previousParameter = currentParameter;
        }
        PsiHelpers.moveToPsiElement(previousParameter);
    }
}
