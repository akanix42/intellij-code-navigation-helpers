package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.JSArgumentList;
import com.intellij.lang.javascript.psi.JSExpression;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToPreviousArgument extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement currentElement = PsiHelpers.getCurrentElement(event);
        PsiElement boundingElement = PsiHelpers.getSelfOrParentOfType(currentElement, JSArgumentList.class);

        /* If we're in an argument list, find the previous argument */
        if (boundingElement != null) {
            PsiElement currentArgument = PsiHelpers.getSelfOrParentOfType(currentElement, JSExpression.class, boundingElement);
            /* Handle if we're not currently focused on an argument - maybe we're on whitespace or a comma */
            if (currentArgument == null) {
                currentArgument = currentElement;
            }
            PsiElement previousArgument = PsiHelpers.getPreviousSiblingOfType(currentArgument, JSExpression.class);
            /* If there is no previous argument, move to the beginning of the current element */
            if (previousArgument == null) {
                previousArgument = currentArgument;
            }
            PsiHelpers.moveToPsiElement(previousArgument);
        }
    }
}
