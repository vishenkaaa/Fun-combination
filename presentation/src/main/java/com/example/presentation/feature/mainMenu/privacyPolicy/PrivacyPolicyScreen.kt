package com.example.presentation.feature.mainMenu.privacyPolicy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.common.ui.components.PolicySection

@Composable
fun PrivacyPolicyRoute(
    onNavigateBack: () -> Unit,
) {
    PrivacyPolicyScreen(
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.privacy_policy),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PolicySection(
                title = stringResource(R.string.pp_section_1_title),
                content = stringResource(R.string.pp_section_1_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_2_title),
                content = stringResource(R.string.pp_section_2_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_3_title),
                content = stringResource(R.string.pp_section_3_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_4_title),
                content = stringResource(R.string.pp_section_4_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_5_title),
                content = stringResource(R.string.pp_section_5_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_6_title),
                content = stringResource(R.string.pp_section_6_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_7_title),
                content = stringResource(R.string.pp_section_7_content)
            )

            PolicySection(
                title = stringResource(R.string.pp_section_8_title),
                content = stringResource(R.string.pp_section_8_content)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.pp_copyright),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}