package com.nathantreid.codeNavigationHelpers.Actions;


import com.intellij.lang.javascript.JSTokenTypes;
import com.intellij.lang.javascript.psi.JSReferenceExpression;
import com.intellij.lang.javascript.psi.ecmal4.JSClass;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.nathantreid.codeNavigationHelpers.Common.PsiHelpers;


public class MoveCaretToClassNameAction extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        PsiElement element = PsiHelpers.getCurrentElement(event);
        element = PsiHelpers.getSelfOrParentOfType(element, JSClass.class);
        element = PsiHelpers.getChildElement(element, JSReferenceExpression.class);
        element = PsiHelpers.getChildElement(element, LeafPsiElement.class, JSTokenTypes.IDENTIFIER);
        PsiHelpers.moveToPsiElement(element);
    }

}
