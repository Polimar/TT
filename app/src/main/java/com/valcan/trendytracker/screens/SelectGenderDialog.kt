package com.valcan.trendytracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valcan.trendytracker.R

@Composable
fun SelectGenderDialog(
    onDismiss: () -> Unit,
    onGenderSelected: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Seleziona Genere") },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onGenderSelected(true) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_male),
                        contentDescription = "Maschio",
                        modifier = Modifier.size(100.dp)
                    )
                    Text("Maschio")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onGenderSelected(false) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_female),
                        contentDescription = "Femmina",
                        modifier = Modifier.size(100.dp)
                    )
                    Text("Femmina")
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
} 