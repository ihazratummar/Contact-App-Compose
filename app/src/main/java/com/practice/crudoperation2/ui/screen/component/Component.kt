package com.practice.crudoperation2.ui.screen.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.crudoperation2.ui.theme.CrudOperation2Theme

@Composable
fun CustomTextField(
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardtype: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    singleLive: Boolean = true,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        label = label,
        value = value,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardtype,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions.Default,
        singleLine = singleLive,
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun DeleteContactDialog(
    modifier: Modifier = Modifier,
    label: String = "Delete Contact",
    description: String = "Are you sure you want to delete this contact?",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Card(
        modifier = modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = description)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(onClick = onDismiss) {
                    Text(
                        text = "NO",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = onConfirm,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                    ) {
                    Text(
                        text = "YES",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeleteContactDialogPreview() {
    CrudOperation2Theme {
        DeleteContactDialog()
    }
}