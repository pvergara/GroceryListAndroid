package org.ecos.core.tests.service.messaging.samples;

import com.google.gson.Gson;

import java.util.List;

public class VolumeContainer {
    public List<Volume> volumes;

    public static VolumeContainer newOne() {
        Gson g = new Gson();
        String response = getResponse();
        return g.fromJson(response, VolumeContainer.class);
    }

    private static String getResponse() {
        return "{\n" +
        " \"volumes\": [\n" +
        "  {\n" +
        "   \"status\": \"available\", \n" +
        "   \"managed\": true, \n" +
        "   \"name\": \"va_85621143-1133-412f-83b4-57a01a552638_\", \n" +
        "   \"support\": {\n" +
        "    \"status\": \"supported\"\n" +
        "   }, \n" +
        "   \"storage_pool\": \"pfm9253_pfm9254_new\", \n" +
        "   \"id\": \"afb8e294-6188-4907-9f6f-963c7623cecb\", \n" +
        "   \"size\": 9\n" +
        "  }, \n" +
        "  {\n" +
        "   \"status\": \"in-use\", \n" +
        "   \"managed\": false, \n" +
        "   \"name\": \"bt_newd20\", \n" +
        "   \"support\": {\n" +
        "    \"status\": \"not_supported\", \n" +
        "    \"reasons\": [\n" +
        "     \"This volume is not a candidate for management because it is already attached to a virtual machine.  To manage this volume with PowerVC, select the virtual machine to which the volume is attached for management. The attached volume will be automatically included for management.\"\n" +
        "    ]\n" +
        "   }, \n" +
        "   \"storage_pool\": \"KVM\", \n" +
        "   \"mapped_wwpns\": [\n" +
        "    \"2101001B32BD4280\", \n" +
        "    \"2100001B329D4280\", \n" +
        "    \"2101001B32BD637E\", \n" +
        "    \"2100001B329D637E\"\n" +
        "   ], \n" +
        "   \"id\": \"c7838c79-17ca-3cbc-98e5-3567fde902d8\", \n" +
        "   \"size\": 0\n" +
        "  }, \n" +
        "  {\n" +
        "   \"status\": \"available\", \n" +
        "   \"managed\": true, \n" +
        "   \"name\": \"vdisk138\", \n" +
        "   \"support\": {\n" +
        "    \"status\": \"supported\"\n" +
        "   }, \n" +
        "   \"storage_pool\": \"Chassis2_IBMi\", \n" +
        "   \"id\": \"b6d00783-9f8c-40b8-ad78-956b0299478c\", \n" +
        "   \"size\": 100\n" +
        "\n" +
        "\n" +
        "  }\n" +
        " ]\n" +
        "}";
    }

    public class Volume {
        private String status;
        private Boolean managed;
        private String name;
        private Support support;
        private String storage_pool;
        private String id;
        private int size;
        private List<String> mapped_wwpns;

        public String getId(){return id;}
        public String getName(){return name;}

        public List<String> getMapped_wwpns() {
            return mapped_wwpns;
        }
    }

    private class Support {
        private String status;
        private List<String> reasons;
    }
}