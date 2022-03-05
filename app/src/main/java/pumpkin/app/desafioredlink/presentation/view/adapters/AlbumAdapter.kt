package pumpkin.app.desafioredlink.presentation.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pumpkin.app.desafioredlink.R
import pumpkin.app.desafioredlink.data.model.Album

class AlbumAdapter(
    context: Context,
    private val list: List<Album>) :
    ArrayAdapter<Album>(context, android.R.layout.simple_list_item_1, list) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val albumPosition = list[position]
        val itemList =
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        itemList.findViewById<TextView>(R.id.idAlbum).text = "ID: ${albumPosition.id}"
        itemList.findViewById<TextView>(R.id.nameAlbum).text = albumPosition.title

        return itemList
    }
}