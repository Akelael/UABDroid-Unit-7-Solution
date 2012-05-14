/*
   Copyright 2012 Ruben Serrano

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.uab.deic.uabdroid.solutions.unit7.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver 
{
	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	
	@Override
	public void onReceive(Context _context, Intent _intent) 
	{
		if (_intent.getAction().equalsIgnoreCase(SMS_RECEIVED))
		{
            Bundle bundle = _intent.getExtras();
            if (bundle != null)
            {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages)
                {
                    String msg = message.getMessageBody();
                    Toast toast = Toast.makeText(_context, msg, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
		}
	}
}
