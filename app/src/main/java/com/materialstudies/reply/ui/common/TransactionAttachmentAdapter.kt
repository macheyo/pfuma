/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.materialstudies.reply.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.reply.data.TransactionAttachment

/**
 * Generic RecyclerView.Adapter to display [TransactionAttachment]s.
 */
abstract class TransactionAttachmentAdapter : RecyclerView.Adapter<TransactionAttachmentViewHolder>() {

    private var list: List<TransactionAttachment> = emptyList()

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = getLayoutIdForPosition(position)

    fun submitList(attachments: List<TransactionAttachment>) {
        list = attachments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAttachmentViewHolder {
        return TransactionAttachmentViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TransactionAttachmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    /**
     * Clients should implement this method to determine what layout is inflated for a given
     * position. The layout must include a data parameter named 'emailAttachment' with a type
     * of [TransactionAttachment].
     */
    abstract fun getLayoutIdForPosition(position: Int): Int
}
