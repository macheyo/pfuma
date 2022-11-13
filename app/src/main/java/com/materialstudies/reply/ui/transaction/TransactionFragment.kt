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

package com.materialstudies.reply.ui.transaction

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import com.materialstudies.reply.R
import com.materialstudies.reply.data.TransactionStore
import com.materialstudies.reply.databinding.FragmentTransactionBinding
import com.materialstudies.reply.util.themeColor
import kotlin.LazyThreadSafetyMode.NONE

private const val MAX_GRID_SPANS = 3

/**
 * A [Fragment] which displays a single, full email.
 */
class TransactionFragment : Fragment() {

    private val args: TransactionFragmentArgs by navArgs()
    private val transactionId: Long by lazy(NONE) { args.transactionId}

    private lateinit var binding: FragmentTransactionBinding
    private val attachmentAdapter = TransactionAttachmentGridAdapter(MAX_GRID_SPANS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        val transaction = TransactionStore.get(transactionId)
        if (transaction == null) {
            showError()
            return
        }

        binding.run {
            this.transaction = transaction

            // Set up the staggered/masonry grid recycler
            attachmentRecyclerView.layoutManager = GridLayoutManager(
                requireContext(),
                MAX_GRID_SPANS
            ).apply {
                spanSizeLookup = attachmentAdapter.variableSpanSizeLookup
            }
            attachmentRecyclerView.adapter = attachmentAdapter
            attachmentAdapter.submitList(transaction.attachments)
        }
    }

    private fun showError() {
        // Do nothing
    }
}