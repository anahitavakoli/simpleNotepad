package com.anahitavakoli.apps.simplenotepad.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.anahitavakoli.apps.simplenotepad.R
import com.anahitavakoli.apps.simplenotepad.database.NoteDbAdapter
import com.anahitavakoli.apps.simplenotepad.model.Note

class NoteAdapter(mContext : Context ,list : MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.NoteVH>() {


    val noteList = list
    val context = mContext
    var noteDbAdapter : NoteDbAdapter = NoteDbAdapter(context)

    class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<AppCompatTextView>(R.id.title_recycler)
        val description = itemView.findViewById<AppCompatTextView>(R.id.desc_recycler)
        val imgDelete = itemView.findViewById<AppCompatImageView>(R.id.img_delete)
        val imgShare = itemView.findViewById<AppCompatImageView>(R.id.img_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {

        val view = LayoutInflater.from(context).inflate(R.layout.notes_row,null)
        return NoteVH(view)
    }

    override fun getItemCount(): Int {
        return noteList.count()
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {

        val note = noteList.get(position)
        holder.title.text = note.title.toString()
        holder.description.text = note.description
        holder.imgDelete.setOnClickListener{
        val result = note.id?.let { it1 -> noteDbAdapter.deleteNote(it1) }
        noteList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position,noteList.size)
        }

        holder.imgShare.setOnClickListener{
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,note.description)
            intent.putExtra(Intent.EXTRA_SUBJECT,note.title)
            intent.type="text/plain"
            context.startActivity(Intent.createChooser(intent,"Share To:"))
        }

    }

}