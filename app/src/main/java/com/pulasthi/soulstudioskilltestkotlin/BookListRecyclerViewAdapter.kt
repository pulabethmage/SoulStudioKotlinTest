package com.pulasthi.soulstudioskilltestkotlin

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class BookListRecyclerViewAdapter(
    loadBookActivity: LoadBookActivity,
    Imgv_bookthum: ImageView?,
    Tv_bookTitle: TextView?,
    Tv_bookDes: TextView?,
    arraybookthumbnails: ArrayList<String>,
    arraybooktitles: ArrayList<String>,
    arraybookdescriptions: ArrayList<String>
) : RecyclerView.Adapter<BookListRecyclerViewAdapter.BookListRecyclerViewHolder>() {
    //private var flowerCount: Int = 0

    var context: Context? = loadBookActivity

    var img_book_thumb: ImageView? = Imgv_bookthum
    var tv_book_title: TextView? = Tv_bookTitle
    var tv_book_desc: TextView? = Tv_bookDes

    var array_img_book_thumb = arraybookthumbnails
    var array_tv_book_title = arraybooktitles
    var array_tv_book_desc = arraybookdescriptions



    /* ViewHolder for displaying header. */
    class BookListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //private val flowerNumberTextView: TextView = itemView.findViewById(R.id.flower_number_text)
         var img_book_thumb: ImageView = itemView.findViewById(R.id.book_tumbnail)
         var tv_book_title: TextView = itemView.findViewById(R.id.book_title)
         var tv_book_des: TextView = itemView.findViewById(R.id.book_description)
        fun bind() {
            //flowerNumberTextView.text = flowerCount.toString()

        }
    }




    /* Inflates view and returns BookListRecyclerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.singlebookitem, parent, false)
        return BookListRecyclerViewHolder(view)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {

        //holder.img_book_thumb.setImageURI(reqId.get(position));
        Picasso.get().load(array_img_book_thumb[position]).into(holder.img_book_thumb)
        holder.tv_book_title.setText(array_tv_book_title[position])
        holder.tv_book_des.setText(array_tv_book_desc[position])
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return array_img_book_thumb.size
    }


}