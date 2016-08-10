package com.justforyou.bestnarutosongs;

public class Songs
{
    private String mNameOfSong;
    private String mDeveloperRate;
    private int mSong;
    private int mImage;

    public Songs(String mnameofsong, String mdeveloperrate, int msong, int mimage)
    {
        mNameOfSong = mnameofsong;
        mDeveloperRate = mdeveloperrate;
        mSong = msong;
        mImage = mimage;
    }

    public String getNameOfSong()
    {
        return (mNameOfSong);
    }

    public String getDeveloperRate()
    {
        return (mDeveloperRate);
    }

    public int getImage()
    {
        return (mImage);
    }

    public int getSong()
    {
        return (mSong);
    }

}
