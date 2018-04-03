package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.psi.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToNextArgument extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement currentElement = PsiHelpers.getCurrentElement(event);
        PsiElement boundingElement = PsiHelpers.getSelfOrParentOfType(currentElement, JSArgumentList.class);

        /* If we're in an argument list, find the next argument */
        if (boundingElement != null) {
            PsiElement currentArgument = PsiHelpers.getSelfOrParentOfType(currentElement, JSExpression.class, boundingElement);
            /* Handle if we're not currently focused on an argument - maybe we're on whitespace or a comma */
            if (currentArgument == null) {
                currentArgument = currentElement;
            }
            PsiElement nextArgument = PsiHelpers.getNextSiblingOfType(currentArgument, JSExpression.class);
            /* If there is a next argument, move to it */
            if (nextArgument != null) {
                PsiHelpers.moveToPsiElement(nextArgument);
                return;
            }
            /* If there is no next argument, move to the end of the argument list */
            PsiElement endOfArgumentList = PsiHelpers.getLastSibling(currentArgument);
            PsiHelpers.moveToPsiElement(endOfArgumentList);
            return;
        }

        /* If we're not in an argument list but we are in a call expression, find the argument list */
        PsiElement element = PsiHelpers.getSelfOrParentOfType(currentElement, JSCallExpression.class);
        element = PsiHelpers.getChildElement(element, JSArgumentList.class);
        element = PsiHelpers.getChildElement(element, JSLiteralExpression.class);
        PsiHelpers.moveToPsiElement(element);
    }
}
