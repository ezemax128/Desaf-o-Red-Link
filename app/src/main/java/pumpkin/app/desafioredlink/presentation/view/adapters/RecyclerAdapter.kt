package pumpkin.app.desafioredlink.presentation.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import pumpkin.app.desafioredlink.R
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.databinding.ItemPhotoBinding

class RecyclerAdapter(private val list: List<Photo>) :
    RecyclerView.Adapter<RecyclerAdapter.PhotoAdapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter {
        return PhotoAdapter(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoAdapter, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PhotoAdapter(item: View) : RecyclerView.ViewHolder(item) {
        @SuppressLint("SetTextI18n")
        fun bind(photo: Photo) {
            val binding = ItemPhotoBinding.bind(itemView)
            binding.imageView.load(photo.url)
            binding.photoItemId.text = "ID: ${photo.id}"
            binding.photoItemTitle.text = photo.title
        }
    }
}