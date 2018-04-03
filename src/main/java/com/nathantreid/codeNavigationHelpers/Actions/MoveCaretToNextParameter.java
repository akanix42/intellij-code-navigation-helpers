package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.JSParameter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToNextParameter extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement element = PsiHelpers.getCurrentElement(event);
        PsiElement currentParameter = PsiHelpers.getSelfOrParentOfType(element, JSParameter.class);
        if (currentParameter == null) {
            currentParameter = element;
        }

        PsiElement nextParameter = PsiHelpers.getNextSiblingOfType(currentParameter, JSParameter.class);
        if (nextParameter != null) {
            PsiHelpers.moveToPsiElement(nextParameter);
            return;
        }

        PsiElement endOfArgumentList = PsiHelpers.getLastSibling(currentParameter);
        PsiHelpers.moveToPsiElement(endOfArgumentList);
    }
}
