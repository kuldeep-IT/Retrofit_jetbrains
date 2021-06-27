package com.kuldeepjetpackkotlin.retrofitjetbrains


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

   private lateinit var retService: AlbumServiece

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retService = RetrofitInstance.getRetrofitInstance()
            .create(AlbumServiece::class.java)


//        getRequestQueryWithParameter()
//        getRequestPathQuery()

        postUploadAlbum()
    }

    private fun getRequestQueryWithParameter()
    {

        val responseLiveData: LiveData<Response<Albums>> = liveData {

//          val reponse = retService.getAlbums()
            val reponse = retService.getSortedAlbum(3)
            emit(reponse) //emit means myData.value = someValue
        }

        responseLiveData.observe(this, Observer {

            val albumList = it.body()?.listIterator()
            //list iterator -> get list into one by one

            if (albumList!=null)
            {
                while (albumList.hasNext())
                {
                    val albumsItem = albumList.next()

//                    Log.d("HEY", albumsItem.title)

                    val result = "Album id :${albumsItem.id} \n "+
                            "Album title :${albumsItem.title} \n "+
                            "User id :${albumsItem.userId} \n\n\n "

                    tv.append(result)
                }
            }


        })
    }

    private fun getRequestPathQuery()
    {

        //for the path
        val responsePathLiveData: LiveData<Response<AlbumsItem>> = liveData {

            val responsePath = retService.getPathAlbum(3)
            emit(responsePath)
        }

        responsePathLiveData.observe(this, Observer {

            val albumsPath = it.body()?.title
            Toast.makeText(applicationContext, albumsPath, Toast.LENGTH_SHORT).show()

        })

    }

    //post data

    private fun postUploadAlbum()
    {
        val albumsItem = AlbumsItem(1, " JAI HO", 108)

        val reponsePostLiveData: LiveData<Response<AlbumsItem>> = liveData {

            val responsePost = retService.postData(albumsItem)
            emit(responsePost)

        }

        reponsePostLiveData.observe(this, Observer {

            val receivedAlbumItem = it.body()

            val result = "Album id :${receivedAlbumItem?.id} \n "+
                    "Album title :${receivedAlbumItem?.title} \n "+
                    "User id :${receivedAlbumItem?.userId} \n\n\n "

            tv.append(result)

        })


    }
}