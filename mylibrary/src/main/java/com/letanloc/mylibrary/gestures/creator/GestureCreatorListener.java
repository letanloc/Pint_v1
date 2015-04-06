package com.letanloc.mylibrary.gestures.creator;


import com.letanloc.mylibrary.draw.SerializablePath;

public interface GestureCreatorListener {
  void onGestureCreated(SerializablePath serializablePath);

  void onCurrentGestureChanged(SerializablePath currentDrawingPath);
}
