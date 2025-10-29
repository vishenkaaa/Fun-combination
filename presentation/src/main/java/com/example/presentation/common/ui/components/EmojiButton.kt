package com.example.presentation.common.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.GameEmoji

@Composable
fun EmojiButton(
    emoji: GameEmoji,
    onClick: () -> Unit,
    enabled: Boolean,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .size(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.6f),
            disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = emoji.emoji,
            fontSize = 48.sp
        )
    }
}