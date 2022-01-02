package com.practice.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.practice.retrofitpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var retrofitInstance: AlbumServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        retrofitInstance = RetrofitInstance.getInstance().create(AlbumServices::class.java)
        uploadPostRequest()

    }

    fun getCompleteRequest(){
        val responseData: LiveData<Response<Albums>> = liveData {
            val response: Response<Albums> = retrofitInstance.getSortedAlbums(3)
            emit(response)
        }

        responseData.observe(this, Observer {
            it.body()?.forEach {
                binding.textData.text =
                    binding.textData.text.toString() + "${it.id} \n${it.title}  \n${it.userId} \n\n"
            }
        })
    }

    fun getRequestWithQueryParameter(){
        val responseData: LiveData<Response<Albums>> = liveData {
            val response: Response<Albums> = retrofitInstance.getAlbums()
            emit(response)
        }

        responseData.observe(this, Observer {
            it.body()?.forEach {
                binding.textData.text =
                    binding.textData.text.toString() + "${it.id} \n${it.title}  \n${it.userId} \n\n"
            }
        })
    }

    fun getRequestWithPathParameter(){
        val retrofitData: LiveData<Response<AlbumsItem>> = liveData {
            val response: Response<AlbumsItem> = retrofitInstance.getAlbum(3)
            emit(response)
        }

        retrofitData.observe(this, Observer {
            binding.textData.text = it.body()?.title
        })
    }
    fun uploadPostRequest(){
        val albums: AlbumsItem = AlbumsItem(1,"Album post request made",3)
        val retrofitData: LiveData<Response<AlbumsItem>> = liveData {
            val response: Response<AlbumsItem> = retrofitInstance.postAlbum(albums)
            emit(response)
        }

        retrofitData.observe(this, Observer {
            binding.textData.text = it.body()?.title
        })
    }
}