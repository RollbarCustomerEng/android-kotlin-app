package com.example.ns_poc
import com.rollbar.api.payload.data.Data;
import com.rollbar.notifier.transformer.Transformer;


public class PayloadTransformer : Transformer {
    override fun transform(data: Data): Data {

        // Use thsi transform method to do advanced data scrubbing
        // and modification of the payload (data) before sending the data

        // add some additional custom data fields
        // where data is only available at time error occurs
        var map = data.getCustom()
        if(map == null){
            map = HashMap<String, Any?>()
        }

        map["xyz"] = "wxv"
        var newData = Data.Builder(data)
            .custom(map)
            .build()

        return newData
    }
}