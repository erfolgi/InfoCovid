package com.primateknos.infocovid_19.materi

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.primateknos.infocovid_19.R
import com.shockwave.pdfium.PdfDocument
import java.io.*

class DetailActivity : AppCompatActivity(), OnLoadCompleteListener {
    val SAMPLE_FILE = "bab1.pdf"

    private val mShareActionProvider: ShareActionProvider? = null
    lateinit var pdfView: PDFView
    internal var pageNumber: Int? = 5
    lateinit var pdfFileName: String
    private val MY_PERMISSION_RQUEST_STORAGE = 1
    companion object {
        var extrafile="0"
        var extratitle="nama title"
        var extrapdf="uuno8tahun1999.pdf"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        extratitle=intent.getStringExtra(extratitle)
        supportActionBar?.title = extratitle
        extrafile=intent.getStringExtra(extrafile)
        extrapdf=intent.getStringExtra(extrapdf)
        pdfView = findViewById<PDFView>(R.id.pdfView)
        displayFromAsset(extrapdf)
        if (ContextCompat.checkSelfPermission(
                this@DetailActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@DetailActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@DetailActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), MY_PERMISSION_RQUEST_STORAGE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@DetailActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), MY_PERMISSION_RQUEST_STORAGE
                )
            }
        } else {
            //DO NOTHING
        }
    }

    private fun displayFromAsset(assetFileName: String) {
        pdfFileName = assetFileName

        pdfView.fromAsset(extrapdf)
            .defaultPage(extrafile.toInt())
            .enableSwipe(true)
            .swipeHorizontal(false)
            //                .onPageChange(onPageChanged(1,2))
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(this))
            .load()
    }

    fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        title = String.format("%s %s / %s", pdfFileName, page + 1, pageCount)
    }

    override fun loadComplete(nbPages: Int) {
        val meta = pdfView.documentMeta
        printBookmarksTree(pdfView.tableOfContents, "-")
    }

    fun printBookmarksTree(tree: kotlin.collections.List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {

            Log.e("Error: ", String.format("%s %s, p %d", sep, b.title, b.pageIdx))

            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }
    override fun onPointerCaptureChanged(hasCapture: Boolean) {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_download, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // action with ID action_refresh was selected
            R.id.action_download ->
                //                try {
                //                    saveImageToExternalStorage(getAssets("tata_cara.pdf"));
                //                } catch (IOException e) {
                //                    e.printStackTrace();
                //                }
                copyAsset(extrapdf)

            else -> {
            }
        }

        return true
    }

    private fun copyAsset(filename: String) {
        val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/Info_Covid"
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val assetManager = assets
        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            `in` = assetManager.open(filename)
            val outFile = File(dirPath, filename)
            out = FileOutputStream(outFile)
            copyFile(`in`!!, out)
            Toast.makeText(this, "Saved to Info_Covid Folder", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed= $e", Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int

        read = `in`.read(buffer)
        while (read != -1) {
            out.write(buffer, 0, read)
            read = `in`.read(buffer)
        }
    }

}