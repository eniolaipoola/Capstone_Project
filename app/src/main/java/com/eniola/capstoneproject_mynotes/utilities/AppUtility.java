package com.eniola.capstoneproject_mynotes.utilities;

import java.util.UUID;

/**
 * Copyright (c) 2019 Eniola Ipoola
 * All rights reserved
 * Created on 31-Dec-2019
 */
public class AppUtility {

    public String generateUniqueId(){
        return UUID.randomUUID().toString();
    }
}
