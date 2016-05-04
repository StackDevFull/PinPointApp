package com.tenpearls.android.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;

import com.tenpearls.android.R;
import com.tenpearls.android.utilities.FontUtility;
import com.tenpearls.android.utilities.StringUtility;

/**
 * 
 * {@link android.widget.EditText}'s subclass to assist in overriding default
 * drawing behavior. Mainly to allow single point of change across the whole
 * application.
 *
 */
public class EditText extends android.widget.EditText {

	/**
	 * Instantiates a new edits the text.
	 * 
	 * @param context the context
	 */
	public EditText (Context context) {

		super(context);
		init(null, 0);
	}

	/**
	 * Instantiates a new edits the text.
	 * 
	 * @param context the context
	 * @param attrs the attrs
	 */
	public EditText (Context context, AttributeSet attrs) {

		super(context, attrs);
		init(attrs, 0);
	}

	/**
	 * Instantiates a new edits the text.
	 * 
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public EditText (Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init (AttributeSet attrs, int defStyle) {
		if(isInEditMode()) {
			return;
		}

		if (attrs == null)
			return;

		final TypedArray attributes = getContext ().obtainStyledAttributes (attrs, R.styleable.Generic, defStyle, 0);

		setCustomTypeface (attributes);

		attributes.recycle();
	}

	private void setCustomTypeface (final TypedArray attributes) {

		String fontPathFromAssets;

		int attributeResourceValue = attributes.getResourceId (R.styleable.Generic_font_path_from_assets, -1);

		if (attributeResourceValue < 0) {
			fontPathFromAssets = attributes.getString(R.styleable.Generic_font_path_from_assets);
		}
		else {
			fontPathFromAssets = getContext ().getString (attributeResourceValue);
		}


		if(StringUtility.isEmptyOrNull(fontPathFromAssets)){
			fontPathFromAssets = getContext().getString(R.string.font_default);
		}

		if(StringUtility.isEmptyOrNull(fontPathFromAssets)) {
			return;
		}


		Typeface typeface = FontUtility.getFontFromAssets (fontPathFromAssets, getContext ());

		if(typeface == null) {
			return;
		}

		setTypeface (typeface, Typeface.NORMAL);
	}
}
