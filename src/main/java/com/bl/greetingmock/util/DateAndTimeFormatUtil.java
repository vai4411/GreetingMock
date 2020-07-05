/**********************************************************************
 * @purpose : CurrentDateAndTime Use For Providing Date And Time
 * @author : Vaibhav Patil
 **********************************************************************/
package com.bl.greetingmock.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeFormatUtil {

    /**+
     * @purpose : Display Current Date And Time
     * @return : Current Date And Time
     */
    public static String currentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
