package com.nathantreid.codeNavigationHelpers.Common;

import com.intellij.lang.javascript.psi.JSExpression;
import com.intellij.lang.javascript.psi.JSFunction;
import com.intellij.lang.javascript.psi.ecma6.TypeScriptFunction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;

public class PsiHelpers {

    public static <T extends PsiElement> PsiElement getChildElement(PsiElement element, Class<T> _class) {
        if (element == null) {
            return element;
        }

        return PsiTreeUtil.getChildOfType(element, _class);
    }

    public static <T extends PsiElement> PsiElement getChildElement(PsiElement element, Class<T> _class, String elementType) {
        if (element == null) {
            return element;
        }

        return PsiTreeUtil.getChildrenOfAnyType(element, _class)
                .stream()
                .filter(o -> o.getNode().getElementType().toString().equals("JS:IDENTIFIER"))
                .findFirst()
                .orElse(null);
    }

    public static <T extends PsiElement> PsiElement getChildElement(PsiElement element, Class<T> _class, IElementType elementType) {
        return getChildElement(element, _class, elementType.toString());
    }

    public static PsiElement getCurrentElement(AnActionEvent event) {
        if (event.getProject() == null) {
            return null;
        }
        Editor editor = FileEditorManager.getInstance(event.getProject()).getSelectedTextEditor();
        if (editor == null) {
            return null;
        }
        PsiFile file = event.getData(PlatformDataKeys.PSI_FILE);
        return file.findElementAt(editor.getCaretModel().getOffset());
    }

    public static <T extends PsiElement> PsiElement getSelfOrParentOfType(PsiElement element, Class<T> _class) {
        if (element == null || _class.isInstance(element)) {
            return element;
        }

        return PsiTreeUtil.getParentOfType(element, _class);
    }

    public static <T extends PsiElement> PsiElement getSelfOrParentOfType(PsiElement element, Class<T> _class, PsiElement boundingElement) {
        if (element == null || _class.isInstance(element)) {
            return element;
        }
        if (element == boundingElement) {
            return null;
        }
        PsiElement parentElement = element.getParent();
        return getSelfOrParentOfType(parentElement, _class, boundingElement);
    }

    public static PsiElement getLastSibling(PsiElement element) {
        if (element == null) {
            return null;
        }
        PsiElement parent = element.getParent();
        if (parent == null) {
            return null;
        }

        return parent.getLastChild();
    }

    public static <T extends PsiElement> PsiElement getPreviousSiblingOfType(PsiElement element, Class<T> _class) {
        if (element == null) {
            return null;
        }

        return PsiTreeUtil.getPrevSiblingOfType(element, _class);
    }

    public static <T extends PsiElement> PsiElement getNextSiblingOfType(PsiElement element, Class<T> _class) {
        if (element == null) {
            return null;
        }

        return PsiTreeUtil.getNextSiblingOfType(element, _class);
    }

    public static <T extends PsiElement> PsiElement getNextSiblingOfType(PsiElement element, Class<T> _class, IElementType elementType) {
        if (element == null) {
            return null;
        }

        element = PsiTreeUtil.getNextSiblingOfType(element, _class);
        if (element == null) {
            return null;
        }

        if (element.getNode().getElementType() == elementType) {
            return element;
        }
        return getNextSiblingOfType(element, _class, elementType);
    }

    public static <T extends PsiElement> PsiElement getPreviousSiblingOrParentSiblingOfType(PsiElement element, Class<T> _class) {
        if (element == null) {
            return null;
        }

        PsiElement targetElement = PsiTreeUtil.getPrevSiblingOfType(element, _class);
        if (targetElement != null) {
            return targetElement;
        }

        return getPreviousSiblingOrParentSiblingOfType(element.getParent(), _class);
    }

    public static <T extends PsiElement> PsiElement getNextSiblingOrParentSiblingOfType(PsiElement element, Class<T> _class) {
        if (element == null) {
            return null;
        }

        PsiElement targetElement = PsiTreeUtil.getNextSiblingOfType(element, _class);
        if (targetElement != null) {
            return targetElement;
        }

        return getNextSiblingOrParentSiblingOfType(element.getParent(), _class);
    }

    public static void moveToPsiElement(PsiElement element) {
        if (element == null) {
            return;
        }
        Editor editor = FileEditorManager.getInstance(element.getProject()).getSelectedTextEditor();
        editor.getCaretModel().moveToOffset(element.getTextRange().getStartOffset());
    }

    public static void moveToEndOfPsiElement(PsiElement element) {
        if (element == null) {
            return;
        }
        Editor editor = FileEditorManager.getInstance(element.getProject()).getSelectedTextEditor();
        editor.getCaretModel().moveToOffset(element.getTextRange().getEndOffset());
    }

}
