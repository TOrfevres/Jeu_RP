package com.environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 12/12/2016.
 */
public class Pieces {
    private String nom;
    private String description;
    private Map<String,Pieces> sorties = new HashMap<String, Pieces>();
}
