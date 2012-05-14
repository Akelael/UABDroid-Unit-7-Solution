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

package org.uab.deic.uabdroid.solutions.unit7;

import org.uab.deic.uabdroid.solutions.unit7.services.AudioService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{	
	private AudioService mAudioService;
	
    @Override
    public void onCreate(Bundle _savedInstanceState) 
    {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        
        final Intent intentService = new Intent(this, AudioService.class);
        
        Button button = (Button) findViewById(R.id.button_start_player);
        button.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View _view) 
			{
				if (mAudioService != null)
				{
					mAudioService.startPlayer();
				}
			}
		});
        
        button = (Button) findViewById(R.id.button_stop_player);
        button.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View _view) 
			{
				if (mAudioService != null)
				{
					mAudioService.stopPlayer();
				}
			}
		});
        
        button = (Button) findViewById(R.id.button_start_service);
        button.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View _view) 
			{
				startService(intentService);
			}
		});
        
        button = (Button) findViewById(R.id.button_stop_service);
        button.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View _view) 
			{
				mAudioService.stopPlayer();
				stopService(intentService);
			}
		});
        
        bindService(intentService, mConnection, Context.BIND_AUTO_CREATE);
    }
    
    @Override
    public void onDestroy()
    {
    	unbindService(mConnection);
    	super.onDestroy();
    }
    
    private ServiceConnection mConnection = new ServiceConnection()
    {
		@Override
		public void onServiceConnected(ComponentName _name, IBinder _serviceBinder) 
		{
			mAudioService = ((AudioService.MyBinder) _serviceBinder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName _name) 
		{
			mAudioService = null;
		}
    };
}

