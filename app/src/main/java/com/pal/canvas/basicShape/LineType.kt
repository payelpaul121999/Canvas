package com.pal.canvas.basicShape

sealed class LineType{
    object Normal:LineType()
    object FiveStep:LineType()
    object TenStep:LineType()
}
