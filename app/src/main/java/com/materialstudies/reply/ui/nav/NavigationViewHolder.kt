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

package com.materialstudies.reply.ui.nav

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.reply.databinding.NavDividerItemLayoutBinding
import com.materialstudies.reply.databinding.NavTransactionFolderItemLayoutBinding
import com.materialstudies.reply.databinding.NavMenuItemLayoutBinding

sealed class NavigationViewHolder<T : NavigationModelItem>(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(navItem : T)

    class NavMenuItemViewHolder(
        private val binding: NavMenuItemLayoutBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavigationModelItem.NavMenuItem>(binding.root) {

        override fun bind(navItem: NavigationModelItem.NavMenuItem) {
            binding.run {
                navMenuItem = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }

    class NavDividerViewHolder(
        private val binding: NavDividerItemLayoutBinding
    ) : NavigationViewHolder<NavigationModelItem.NavDivider>(binding.root) {
        override fun bind(navItem: NavigationModelItem.NavDivider) {
            binding.navDivider = navItem
            binding.executePendingBindings()
        }
    }

    class EmailFolderViewHolder(
        private val binding: NavTransactionFolderItemLayoutBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavigationModelItem.NavTransactionFolder>(binding.root) {

        override fun bind(navItem: NavigationModelItem.NavTransactionFolder) {
            binding.run {
                navTransactionFolder = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }
}