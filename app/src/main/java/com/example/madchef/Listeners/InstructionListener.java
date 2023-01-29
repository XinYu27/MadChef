package com.example.madchef.Listeners;

import com.example.madchef.Models.InstructionsResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
