package com.selland.model;

public enum Meal {
	
	BREAKFAST("breakfast", "breakfast"),
	LUNCH("lunch", "lunch"),
	DINNER("dinner", "dinner"),
	SNACK("snack", "snack"),
	LAST("last", "last meal");
	
	private String id;
	private String name;
	
	private Meal(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public static Meal findById(String id) {
		
        if (id != null) {
            for (Meal m : Meal.values()) {
                if (id.equalsIgnoreCase(m.getId())) {
                    return m;
                }
            }
        }
        
        return null;
	}
	
	public static Meal findByName(String nm) {
		
        if (nm != null) {
            for (Meal m : Meal.values()) {
                if (nm.equalsIgnoreCase(m.getName())) {
                    return m;
                }
            }
        }
        
        return null;
	}
}
