package com.sagar.jaxb.adapters;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.sagar.jaxb.domain.Item;
import java.util.stream.Collectors;


public class ItemsAdapter extends XmlAdapter<ItemsWrapper, Map<String, Item>> {

	 @Override
	    public ItemsWrapper marshal(Map<String, Item> map) throws Exception {
	        return new ItemsWrapper(map.entrySet().stream()
	                .map(entry -> new ItemValue(entry.getKey(), entry.getValue()))
	                .collect(Collectors.toList()));
	    }

        @Override
        public Map<String, Item> unmarshal(ItemsWrapper wrapper) throws Exception {
            return wrapper.getItems().stream()
                    .collect(Collectors.toMap(ItemValue::getProductCode, ItemValue::toItem));
        }

}
