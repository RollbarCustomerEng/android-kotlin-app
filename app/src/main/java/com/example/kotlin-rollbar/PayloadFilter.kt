package com.example.ns_poc;
import com.rollbar.api.payload.data.Level;
import com.rollbar.notifier.filter.Filter;
import com.rollbar.api.payload.data.Data

public class PayloadFilter : Filter{

    override fun postProcess(data: Data) : Boolean{
        return false

        /*
        if(logic to decide occurrence should not be sent){
            // true means do NOT send the occurrence to Rollbar (do filter the data)
            return true;
        }
        else {
            // false means send the data to Rollbar
            return false;
        }
         */
    }

    override fun preProcess(
        level: Level?,
        error: Throwable?,
        custom: MutableMap<String, Any>?,
        description: String?
    ): Boolean {
        // unused
        return false;
    }

}
