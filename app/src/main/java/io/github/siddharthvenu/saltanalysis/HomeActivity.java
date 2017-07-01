package io.github.siddharthvenu.saltanalysis;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HomeActivity extends AppCompatActivity {

    String updateTimeTag = "last_update_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            updateApp();
        }
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");
        TextView tv = findViewById(R.id.home_header);
        tv.setTypeface(tf);
        tv = findViewById(R.id.acid_radical_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.basic_radical_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.long_procedure_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.group_separation_text_view);
        tv.setTypeface(tf);

        CardView basicRadical = findViewById(R.id.basic_card_home),
                acidicRadical = findViewById(R.id.acid_card_home),
                grpSeparation = findViewById(R.id.grp_separation_card_home),
                longProcedure = findViewById(R.id.long_procedure_card_home);

        basicRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(launchNextScreen(false));
            }
        });
        acidicRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(launchNextScreen(true));
            }
        });
        grpSeparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, GroupSeparationActivity.class));
            }
        });
        longProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LongProcedureActivity.class));
            }
        });
    }

    private Intent launchNextScreen(boolean isAcidic) {
        return new Intent(this, RadicalListActivity.class).putExtra("isAcidic", isAcidic);
    }
    Uri uri;
    private class UpdateAppTask extends AsyncTask<Void, Void, Void> {

        AlertDialog.Builder builder;

        void downloadApk(String url) {
            String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
            String fileName = "SaltAnalysis.apk";
            destination += fileName;
            //final Uri uri = Uri.parse("file://" + destination);
            uri = FileProvider.getUriForFile(HomeActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(destination));
            //Delete update file if exists
            final File file = new File(destination);
            if (file.exists())
                //noinspection ResultOfMethodCallIgnored
                file.delete();

            //set downloadmanager
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setDescription("App downloading from Github");
            request.setTitle("SaltAnalysis");

            //set destination
            request.setDestinationUri(Uri.parse("file://" + destination));

            // get download service and enqueue file
            final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

            //set BroadcastReceiver to install app when .apk is downloaded
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Intent install = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                            install.setData(uri);
                            install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(install);
                    } else {
                        Uri apkUri = Uri.fromFile(file);
                        Intent install = new Intent(Intent.ACTION_VIEW);
                        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
                        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(install);
                    }

                    unregisterReceiver(this);
                    finish();
                }
            };
            //register receiver for when .apk download is compete
            registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL githubRepo = new URL("https://github.com/sidvenu/SaltAnalysis/releases");
                URLConnection con = githubRepo.openConnection();
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder sb = new StringBuilder();
                String extractData = br.readLine();
                while (extractData != null) {
                    sb.append(extractData);
                    extractData = br.readLine();
                }
                String output = sb.toString();

                String releaseUrl = "/sidvenu/SaltAnalysis/releases/download/";
                int start = output.indexOf(releaseUrl) + releaseUrl.length();

                int endTag = output.indexOf("/", start);
                String latestTag = output.substring(start, endTag).replace('-', ' ').replace("v", "");
                String appTag = BuildConfig.VERSION_NAME;
                //Log.v(getClass().getSimpleName(), "Latest tag: " + latestTag + "\tApp tag: " + appTag);
                if (!TextUtils.equals(latestTag.toLowerCase(), appTag.toLowerCase())) {
                    int endLink = output.indexOf("\"", start);
                    String link = "https://github.com" + releaseUrl + output.substring(start, endLink);
                    //Log.v(getClass().getSimpleName(), link);

                    alertUser(appTag, latestTag, link);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        private void alertUser(String appTag, String latestTag, final String link) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(HomeActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(HomeActivity.this);
            }
            builder.setTitle("Update available")
                    .setMessage("Update to " + latestTag.toLowerCase() + " available\nCurrent app version: " + appTag.toLowerCase())
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            downloadApk(link);
                        }
                    })
                    .setNegativeButton("Remind me later", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                            prefs.edit().putLong(updateTimeTag, System.currentTimeMillis()).apply();
                        }
                    });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    builder.show();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                updateApp();
        }
    }

    void updateApp() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        long startTime = prefs.getLong(updateTimeTag, -1);
        long endTime = System.currentTimeMillis();
        double hoursPassed = (endTime - startTime) / (1000.0 * 60 * 60);
        if (startTime == -1 || hoursPassed > 24) {
            UpdateAppTask updateApp = new UpdateAppTask();
            updateApp.execute();
        }
    }
}
