package com.JBProgramming.someGame.rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class RO {
	
	public int x = 0, y = 0, width = 0, height = 0;
	public float alpha = 1F;
	public boolean visible = false;
	public RT type = null;
	public Map<String, Object> modifiers = new HashMap<String, Object>();

	public RO() {
		init();
	}

	public RO(int x, int y, int width, int height, RT type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		init();
	}

	public RO(int x, int y, int width, int height, RT type, Color c, float alpha, boolean visible) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.alpha = alpha;
		this.visible = visible;
		init();
	}

	private void init() {
		modifiers.put("stroke", new BasicStroke(1));
		modifiers.put("color", new Color(0, 0, 0));
		modifiers.put("fill", true);
	}
	
}
