void HandleMerging()
    {
        for (int i = droplets.Count - 1; i >= 0; i--)
        {
            for (int j = droplets.Count - 1; j > i; j--)
            {
                float distance = Vector2.Distance(droplets[i].position, droplets[j].position);
                if (distance < mergeRadius)
                {
                    // Объединяем капли
                    float totalVolume = Mathf.Pow(droplets[i].radius, 3) + Mathf.Pow(droplets[j].radius, 3);
                    float newRadius = Mathf.Pow(totalVolume, 1f/3f);
                    
                    Vector2 newVelocity = (droplets[i].velocity * droplets[i].radius + 
                                         droplets[j].velocity * droplets[j].radius) / 
                                         (droplets[i].radius + droplets[j].radius);
                    
                    droplets[i].position = (droplets[i].position * droplets[i].radius + 
                                          droplets[j].position * droplets[j].radius) / 
                                         (droplets[i].radius + droplets[j].radius);
                    
                    droplets[i].radius = newRadius;
                    droplets[i].velocity = newVelocity;
                    
                    droplets.RemoveAt(j);
                    Destroy(visualDroplets[j]);
                    visualDroplets.RemoveAt(j);
                }
            }
        }
    }

    void UpdateVisuals()
    {
        for (int i = 0; i < droplets.Count; i++)
        {
            visualDroplets[i].transform.position = droplets[i].position;
            visualDroplets[i].transform.localScale = Vector3.one * droplets[i].radius;
        }
    }

    private class Droplet
    {
        public Vector2 position;
        public Vector2 velocity;
        public float radius;
    }
}