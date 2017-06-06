package org.ecos.grocerylist.core.service;

import org.ecos.grocerylist.core.items.ItemPart;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemStringSplitter {
    public static Map<ItemPart, String> split(String itemString) {
        final Pattern p = Pattern.compile("^([a-zA-Z]+)\\s?\\+?([0-9]+)?");
        return new HashMap<ItemPart,String>(){{
            Matcher m = p.matcher(itemString);
            if(m.matches()) {
                put(ItemPart.name, m.group(1));
                if (m.groupCount() == 2 && m.group(2) != null)
                    put(ItemPart.quantity, m.group(2));
            }
        }};
        }
}
