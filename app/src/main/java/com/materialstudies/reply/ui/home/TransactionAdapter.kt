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

package com.materialstudies.reply.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.materialstudies.reply.data.Transaction
import com.materialstudies.reply.data.TransactionDiffCallback
import com.materialstudies.reply.databinding.TransactionItemLayoutBinding

/**
 * Simple adapter to display Email's in MainActivity.
 */
class TransactionAdapter(
        private val listener: TransactionAdapterListener
) : ListAdapter<Transaction, TransactionViewHolder>(TransactionDiffCallback) {

    interface TransactionAdapterListener {
        fun onEmailClicked(cardView: View, transaction: Transaction)
        fun onEmailLongPressed(transaction: Transaction): Boolean
        fun onEmailStarChanged(transaction: Transaction, newValue: Boolean)
        fun onEmailArchived(transaction: Transaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}