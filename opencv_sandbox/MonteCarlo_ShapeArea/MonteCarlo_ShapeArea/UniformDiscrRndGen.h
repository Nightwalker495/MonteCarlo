#pragma once

#include <random>

namespace app
{
	class UniformDiscrRndGen
	{
	public:
		UniformDiscrRndGen(int min, int max);
		UniformDiscrRndGen(const UniformDiscrRndGen& other);
		~UniformDiscrRndGen();

		UniformDiscrRndGen& operator=(const UniformDiscrRndGen& other);

		int sample() const;

	private:
		std::mt19937* gen_;
		std::uniform_int_distribution<int>* dist_;
	};
}