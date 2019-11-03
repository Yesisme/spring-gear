package com.spring.gear.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.beans.propertyEditor.CustomNumberEditor;

public class CustomNumberEditorTest {

	
	@Test
	public void testConvertString() {
		CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);
		editor.setAsText("3");
		Object value = editor.getValue();
		assertTrue(value instanceof Integer);
		assertEquals(3,((Integer)editor.getValue()).intValue());
		
		editor.setAsText("");
		assertTrue(editor.getValue() ==null);
		
		try {
			editor.setAsText("3.1");
		}catch(IllegalArgumentException e) {
			return;
		}
		Assert.fail();
	}
}
